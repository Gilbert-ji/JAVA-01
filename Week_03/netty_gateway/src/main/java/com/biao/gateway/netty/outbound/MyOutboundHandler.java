package com.biao.gateway.netty.outbound;

import com.biao.gateway.netty.filter.HeaderHttpRequestFilter;
import com.biao.gateway.netty.filter.HeaderHttpResponseFilter;
import com.biao.gateway.netty.filter.HttpRequestFilter;
import com.biao.gateway.netty.filter.HttpResponseFilter;
import com.biao.gateway.netty.router.HttpEndpointRouter;
import com.biao.gateway.netty.router.WeightHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @desc:
 * @author: biao
 * @create: 2021-01-29 21:34
 **/
public class MyOutboundHandler {

    private List<String> urls;
    private ExecutorService threadPoolService;
    private CloseableHttpAsyncClient httpAsyncClient;

    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();
    private HttpResponseFilter responseFilter = new HeaderHttpResponseFilter();
    // 权重路由
    private HttpEndpointRouter router = new WeightHttpEndpointRouter();

    public MyOutboundHandler(List<String> urls){
        this.urls = urls;
        threadPoolService = Executors.newFixedThreadPool(40);

        int cores = Runtime.getRuntime().availableProcessors();
        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpAsyncClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
                    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                        return 6000;
                    }
                })
                .build();
        httpAsyncClient.start();
    }

    public void handle (final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        // 接口路由
        String hitUrl = router.route(urls);
        if(null == hitUrl){
            return;
        }
        final String url = hitUrl + fullRequest.uri();

        // 请求前过滤器
        requestFilter.filter(fullRequest, ctx);
        // 多线程处理请求
        threadPoolService.submit(() -> httpGet(fullRequest, ctx, url));
    }

    private void httpGet(final FullHttpRequest request, final ChannelHandlerContext ctx, String url){
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("key_field", "hello nio!!!!");

        httpAsyncClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                FullHttpResponse response = null;
                try {
                    byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
                    response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
                    response.headers().set("Content-Type", "application/json");
                    response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));
                    // 请求后过滤器
                    responseFilter.filter(response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
                    ctx.close();
                } finally {
                    if (request != null) {
                        if (!HttpUtil.isKeepAlive(request)) {
                            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                        } else {
                            ctx.write(response);
                        }
                    }
                    ctx.flush();
                }
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

}
