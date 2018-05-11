package com.example.liplop.myapplication.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.liplop.myapplication.common.AppConfig;
import com.example.liplop.myapplication.common.WXApiManager;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by shellyford on 2018/3/22.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

    public static final String TAG = WXEntryActivity.class.getSimpleName();
//    public static final String APP_ID = "wxe6cf494c5acd0ce0";
//    public static final String APP_ID = "wxbf199cba0048b4d8";
//    public static final String APP_ID = "wx3ceb24375ea9abe4";  //微信测试
//    public static final String City_Code_Xian = "610100";
    public static final String ykt_id = "20000011";

    private IWXAPI wxapi;

    public static final String WX_STATE = "SDK_sendauth";
    public static final String Auth_STATE = "wechat_sdk";

    public static final String Auth_Code = "authcode";
    public static final int Result_FetchCodeFinish = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wxapi = WXApiManager.getInst().getWxapi();
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
//        int type = baseReq.getType();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType();
        if (type == 1) { //微信授权
            int errcode = baseResp.errCode;
            SendAuth.Resp sendAuthResp = (SendAuth.Resp) baseResp;
            String state = sendAuthResp.state;
            if (WX_STATE.equals(state)) {  //代扣签约调起的微信授权

            }else if (Auth_STATE.equals(state)) { //三方登录授权调起的微信授权
                if (errcode == BaseResp.ErrCode.ERR_OK) {
                }else {
                    finish();
                }
            }
        }else if (type == 2) { //微信好友、朋友圈分享
            int errcode = baseResp.errCode;
            SendMessageToWX.Resp sendMessageResp = (SendMessageToWX.Resp) baseResp;
            if (errcode == BaseResp.ErrCode.ERR_OK) {
                finish();
            }else {
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wxapi.unregisterApp();
        wxapi = null;
    }

}
