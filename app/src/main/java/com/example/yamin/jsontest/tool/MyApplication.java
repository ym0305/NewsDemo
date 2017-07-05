package com.example.yamin.jsontest.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by yamin on 2017/5/30.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext(){
        return context;
    }
}
