package com.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @desc: OKHttpDemo
 * @author: biao
 * @create: 2021-01-23 21:33
 **/
public class OKHttpDemo {

    private static final String URL = "http://localhost:8801/";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(URL).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
