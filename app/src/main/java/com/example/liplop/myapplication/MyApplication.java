package com.example.liplop.myapplication;

import android.app.Application;

import com.example.liplop.myapplication.common.AppConfig;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * Created by hzj on 2018/5/10.
 */

public class MyApplication extends Application {
    private static Application ins;
    @Override
    public void onCreate() {
        super.onCreate();
        ins = this;
        WbSdk.install(MyApplication.getInstance().getApplicationContext(),
                new AuthInfo(MyApplication.getInstance().getApplicationContext(),
                        AppConfig.WB_APP_KEY,AppConfig.WB_REDIRECT_URL,AppConfig.WB_SCOPE));
    }

    public static Application getInstance(){
        return ins;
    }
}
