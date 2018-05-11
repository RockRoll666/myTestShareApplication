package com.example.liplop.myapplication.common;

import android.content.Intent;

import com.example.liplop.myapplication.MyApplication;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by shellyford on 2018/3/22.
 */

public class WXApiManager {
    private static WXApiManager wxApiManager;

    public static final String APP_ID = "wx3ceb24375e9abe4";
    private IWXAPI wxapi;

    private WXApiManager(){
        //是否核验签名
        wxapi = WXAPIFactory.createWXAPI(MyApplication.getInstance().getApplicationContext(), APP_ID, true);
        wxapi.registerApp(APP_ID);
    }

    public static WXApiManager getInst() {
        if (wxApiManager == null) {
            wxApiManager = new WXApiManager();
        }
        return wxApiManager;
    }

    public IWXAPI getWxapi() {
        return wxapi;
    }

    public boolean sendRequest(BaseReq req) {
        return wxapi.sendReq(req);
    }

    public void handleIntent(Intent intent, IWXAPIEventHandler eventHandler) {
        wxapi.handleIntent(intent,eventHandler);
    }
}
