package com.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @desc: HttpClientDemo
 * @author: biao
 * @create: 2021-01-23 21:27
 **/
public class HttpClientDemo {

    private static final String URL = "http://localhost:8801/";

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;

        // 创建http get请求
        HttpGet httpGet = new HttpGet(URL);

        try {
            // 执行GET请求
            httpResponse = httpClient.execute(httpGet);
            // 获取响应实体
            HttpEntity entity = httpResponse.getEntity();
            System.out.println("响应状态为：" + httpResponse.getStatusLine());

            if(null != entity){
                // 响应内容
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            try {
                httpClient.close();
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
