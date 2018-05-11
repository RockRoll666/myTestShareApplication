package com.example.liplop.myapplication;

import android.app.Application;

/**
 * Created by hzj on 2018/5/10.
 */

public class MyApplication extends Application {
    private static Application ins;
    @Override
    public void onCreate() {
        super.onCreate();
        ins = this;
    }

    public static Application getInstance(){
        return ins;
    }
}
