package com.example.liplop.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.liplop.myapplication.R;
import com.example.liplop.myapplication.utis.ImageUtil;
import com.example.liplop.myapplication.utis.ShareUtils;
import com.example.liplop.myapplication.utis.ToastUtil;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements WbShareCallback{
    @BindView(R.id.btn_share_qq)
    Button qq;
    @BindView(R.id.btn_share_qzone)
    Button qzone;
    @BindView(R.id.btn_share_weibo)
    Button weibo;
    @BindView(R.id.btn_share_wxapp)
    Button wxapp;
    @BindView(R.id.btn_share_wxpyq)
    Button wxpyq;

    private WbShareHandler mWbShareHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mWbShareHandler = new WbShareHandler(this);
        mWbShareHandler.registerApp();
    }

    @OnClick({R.id.btn_share_qq,R.id.btn_share_qzone,R.id.btn_share_weibo,R.id.btn_share_wxapp,R.id.btn_share_wxpyq})
    void onBtnClick(View v){
        WXWebpageObject webpageObject;
        WXMediaMessage msg;
        Bundle params;
        switch (v.getId()){
            case R.id.btn_share_wxpyq:
//                        ShareUtils.shareToThirdPlatform(NewsWebActivity.this,ShareType.SHARE_TYPE_WEIXIN_TIMELINE,shareContent);
                webpageObject = new WXWebpageObject("http://www.baidu.com");
                //这个构造方法中自动把传入的bitmap转化为2进制数据,或者你直接传入byte[]也行
                //注意传入的数据不能大于10M,开发文档上写的

                msg = new WXMediaMessage(webpageObject);  //这个对象是用来包裹发送信息的对象
                msg.title = "微信分享";
                msg.description = "分享内容";
                //msg.mediaObject实际上是个IMediaObject对象,
                //它有很多实现类,每一种实现类对应一种发送的信息,
                //比如WXTextObject对应发送的信息是文字,想要发送文字直接传入WXTextObject对象就行

                msg.thumbData = ImageUtil.resToBytes(R.mipmap.ic_launcher);
                //在这设置缩略图
                //官方文档介绍这个bitmap不能超过32kb
                //如果一个像素是8bit的话换算成正方形的bitmap则边长不超过181像素,边长设置成150是比较保险的
                //或者使用msg.setThumbImage(thumbBitmap);省去自己转换二进制数据的过程
                //如果超过32kb则抛异常
                ShareUtils.shareToWXTimeLine(msg);
                break;
            case R.id.btn_share_wxapp:
                webpageObject = new WXWebpageObject("http://www.baidu.com");

                msg = new WXMediaMessage(webpageObject);  //这个对象是用来包裹发送信息的对象
                msg.title = "微信分享";
                msg.description = "分享内容";
                msg.thumbData = ImageUtil.resToBytes(R.mipmap.ic_launcher);
                ShareUtils.shareToWXAPP(msg);
                break;
            case R.id.btn_share_qq:
                params = new Bundle();
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");// 标题
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://www.qq.com/news/1.html");// 内容地址
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"https://www.baidu.com/img/baidu_jgylogo3.gif");// 网络图片地址　　
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "西安地铁");// 应用名称
                params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                ShareUtils.shareToQQ(this,params);
                break;
            case R.id.btn_share_weibo:
                WebpageObject wbWebpageObject = new WebpageObject();
                wbWebpageObject.title = "微博分享";
                wbWebpageObject.description = "分享内容";
                wbWebpageObject.thumbData = ImageUtil.resToBytes(R.mipmap.ic_launcher);
                wbWebpageObject.actionUrl = "http://www.baidu.com";
                ShareUtils.shareToSinaWeibo(mWbShareHandler,wbWebpageObject);
                break;
            case R.id.btn_share_qzone:
                params = new Bundle();
                ArrayList<String> imgs = new ArrayList<>();
                imgs.add("https://www.baidu.com/img/baidu_jgylogo3.gif");
                params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");// 标题
                params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
                params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,"http://www.qq.com/news/1.html");// 内容地址
                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,imgs);// 网络图片地址　　
                //params.putString(QQShare.SHARE_TO_QQ_APP_NAME,"西安地铁");
                //params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                ShareUtils.shareToQZone(this,params);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //qq分享需重写Activity或者Fragment的onActivityResult方法，否则不能正常的监听分享状态
        Tencent.onActivityResultData(requestCode, resultCode, data, new ShareUtils.MyIUiListener());
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, new ShareUtils.MyIUiListener());
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWbShareHandler.doResultIntent(intent,this);
    }

    @Override
    public void onWbShareSuccess() {
        ToastUtil.showToast("分享成功");
    }

    @Override
    public void onWbShareCancel() {
        ToastUtil.showToast("取消分享");
    }

    @Override
    public void onWbShareFail() {
        ToastUtil.showToast("分享失败");
    }
}
