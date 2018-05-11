package com.example.liplop.myapplication.utis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.liplop.myapplication.MyApplication;

import java.io.ByteArrayOutputStream;

/**
 * Created by yindong on 2017/11/1.
 */

public class ImageUtil {

    public static Bitmap drawableToBitmap(Drawable drawable) {
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        final Bitmap bitmap = bitmapDrawable.getBitmap();
        return bitmap;
    }

    public static byte[] resToBytes(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), resId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytesToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
