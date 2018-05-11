package com.example.liplop.myapplication.utis;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.liplop.myapplication.MyApplication;

import java.lang.ref.WeakReference;

/**
 * Created by yindong on 2017/10/20.
 */

public class ToastUtil {
    private static WeakReference<Toast> toastWrapper;
    private static Toast toast;

    /**
     * 全局显示toast，消除连续刷码弹吐司排队现象
     */
    public static void showToast(String text) {
        if (toastWrapper == null || toastWrapper.get() == null) {
            toastWrapper = new WeakReference<Toast>(Toast.makeText(MyApplication.getInstance().getApplicationContext(),text, Toast.LENGTH_SHORT));
            toastWrapper.get().makeText(MyApplication.getInstance().getApplicationContext(),text, Toast.LENGTH_SHORT);
        }
        if (!TextUtils.isEmpty(text)) {
            toastWrapper.get().setText(text);
        }
        toastWrapper.get().setDuration(Toast.LENGTH_SHORT);
        toastWrapper.get().show();
    }

    public static void showToast(int textId) {
        if (toastWrapper == null || toastWrapper.get() == null) {
            toastWrapper = new WeakReference<Toast>(Toast.makeText(MyApplication.getInstance().getApplicationContext(),textId, Toast.LENGTH_SHORT));
            toastWrapper.get().makeText(MyApplication.getInstance().getApplicationContext(),textId, Toast.LENGTH_SHORT);
        }
        toastWrapper.get().setText(textId);
        toastWrapper.get().setDuration(Toast.LENGTH_SHORT);
        toastWrapper.get().show();
    }

    public static void showLongToast(String text) {
        if (toastWrapper == null || toastWrapper.get() == null) {
            toastWrapper = new WeakReference<Toast>(Toast.makeText(MyApplication.getInstance().getApplicationContext(),text, Toast.LENGTH_SHORT));
            toastWrapper.get().makeText(MyApplication.getInstance().getApplicationContext(),text, Toast.LENGTH_LONG);
        }
        if (!TextUtils.isEmpty(text)) {
            toastWrapper.get().setText(text);
        }
        toastWrapper.get().setDuration(Toast.LENGTH_LONG);
        toastWrapper.get().show();
    }

}
