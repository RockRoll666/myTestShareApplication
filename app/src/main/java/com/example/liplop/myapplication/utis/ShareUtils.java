package com.example.liplop.myapplication.utis;

import android.app.Activity;
import android.os.Bundle;

import com.example.liplop.myapplication.MyApplication;
import com.example.liplop.myapplication.common.AppConfig;
import com.example.liplop.myapplication.common.WXApiManager;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 分享工具类
 * Created by hzj on 2018/5/9.
 */

public class ShareUtils {
    private static final int WXAPP = 11;
    private static final int WXTIMELINE = 12;
    /**
     * qq好友
     */
    public static void shareToQQ(final Activity activity,final Bundle params){
        final Tencent tencent = Tencent.createInstance(AppConfig.QQAPPID, MyApplication.getInstance().getApplicationContext());
        // 分享操作要在主线程中完成
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQQ(activity, params, new MyIUiListener());
            }
        });
    }

    /**
     * qq空间
     */
    public static void shareToQZone(final Activity activity,final Bundle params){
        final Tencent tencent = Tencent.createInstance(AppConfig.QQAPPID,MyApplication.getInstance().getApplicationContext());
        // 分享操作要在主线程中完成
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQzone(activity, params, new MyIUiListener());
            }
        });
    }

    public static class MyIUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            // 操作成功
            ToastUtil.showToast("成功");
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtil.showToast("失败:"+uiError.errorMessage);
        }

        @Override
        public void onCancel() {
            // 取消分享
            ToastUtil.showToast("取消分享");
        }
    }

    /**
     * 微信好友
     */
    public static void shareToWXAPP(WXMediaMessage msg){
        shareToWX(WXAPP,msg);
    }

    /**
     * 微信朋友圈
     */
    public static void shareToWXTimeLine(WXMediaMessage msg){
        shareToWX(WXTIMELINE,msg);
    }

    private static void shareToWX(int type,WXMediaMessage msg){
        SendMessageToWX.Req req = new SendMessageToWX.Req();    //创建一个请求对象
        req.message = msg;  //把msg放入请求对象中
        switch (type){
            case WXAPP:
                req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
                break;
            case WXTIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
                break;
            default:
                break;

        }
        req.transaction = "webpage";  //这个tag要唯一,用于在回调中分辨是哪个分享请求
        boolean suc = WXApiManager.getInst().sendRequest(req);   //如果调用成功微信,会返回true
        String s = "";
    }

    public static void shareToSinaWeibo(WbShareHandler wbShareHandler, WebpageObject webpageObject){
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.mediaObject = webpageObject;
        wbShareHandler.shareMessage(weiboMultiMessage,false);
//        SinaWbShareEntryActivity.startActivityByIntent(context,webpageObject);
    }
}
