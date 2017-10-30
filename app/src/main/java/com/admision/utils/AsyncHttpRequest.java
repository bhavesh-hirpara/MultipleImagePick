package com.admision.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AsyncHttpRequest extends OkHttpClient {

    public static Call newRequestPost(Context context, RequestBody body, String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();
        Request.Builder request = new Request.Builder()
                .url(url)
                .post(body);
        Call call = client.newCall(request.build());
        return call;
    }

    public static Call newRequestPut(Context context, RequestBody body, String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();
        Request.Builder request = new Request.Builder()
                .url(url)
                .put(body);
        Call call = client.newCall(request.build());
        return call;
    }

    public static Call newRequestPatch(Context context, RequestBody body, String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();
        Request.Builder request = new Request.Builder()
                .url(url)
                .patch(body);
        Call call = client.newCall(request.build());
        return call;
    }

    public static Call newRequestDelete(Context context, RequestBody body, String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();
        Request.Builder request = new Request.Builder()
                .url(url)
                .delete(body);
        Call call = client.newCall(request.build());
        return call;
    }


    public static Call newRequestGet(Context context, String url) throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();
        Request.Builder request = new Request.Builder()
                .url(url);
        Call call = client.newCall(request.build());
        return call;
    }
}
