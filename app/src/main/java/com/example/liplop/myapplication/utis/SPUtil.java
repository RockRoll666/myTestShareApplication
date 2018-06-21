package com.example.liplop.myapplication.utis;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liplop.myapplication.MyApplication;

/**
 * Created by hzj on 2018/6/21.
 */
public class SPUtil {
    private static SPUtil instance;
    private SharedPreferences sp;

    public static SPUtil getInstance(){
        if (null == instance){
            synchronized (SPUtil.class){
                instance = new SPUtil();
            }
        }
        return instance;
    }

    private SPUtil(){
        sp = MyApplication.getInstance().getSharedPreferences("test", Context.MODE_PRIVATE);
    }

    public void putString(String key,String value){
        sp.edit().putString(key,value).apply();
    }

    public String getString(String key){
        return sp.getString(key,"");
    }
}
