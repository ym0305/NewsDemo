package com.example.yamin.jsontest.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yamin on 2017/5/27.
 */

public class Util {

    public static void sendHttpRequest(String address , okhttp3.Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
