package com.example.liplop.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.liplop.myapplication.utis.ToastUtil;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

/**
 * Created by hzj on 2018/5/10.
 */

public class SinaWbShareEntryActivity extends Activity implements WbShareCallback {
    private WbShareHandler mWbShareHandler;

    public static void startActivityByIntent(Activity from,WebpageObject webpageObject){
        Intent intent = new Intent(from,SinaWbShareEntryActivity.class);
        intent.putExtra("webpageObject",webpageObject);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWbShareHandler = new WbShareHandler(this);
        mWbShareHandler.registerApp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        WebpageObject webpageObject = getIntent().getParcelableExtra("webpageObject");
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.mediaObject = webpageObject;
        mWbShareHandler.shareMessage(weiboMultiMessage, false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWbShareHandler.doResultIntent(intent,this);
    }

    @Override
    public void onWbShareSuccess() {
        ToastUtil.showToast("分享成功");
        finish();
    }

    @Override
    public void onWbShareCancel() {
        ToastUtil.showToast("取消分享");
        finish();
    }

    @Override
    public void onWbShareFail() {
        ToastUtil.showToast("分享失败");
        finish();
    }
}
