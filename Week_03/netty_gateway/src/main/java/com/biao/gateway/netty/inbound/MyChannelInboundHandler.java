package com.biao.gateway.netty.inbound;

import com.biao.gateway.netty.outbound.MyOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

/**
 * @desc: channelHandler
 * @author: biao
 * @create: 2021-01-28 11:48
 **/
public class MyChannelInboundHandler extends ChannelInboundHandlerAdapter {

    private final List<String> urls;
    private MyOutboundHandler handler;

    public MyChannelInboundHandler(List<String> urls){
        this.urls = urls;
        this.handler = new MyOutboundHandler(urls);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;

        handler.handle(fullHttpRequest, ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
