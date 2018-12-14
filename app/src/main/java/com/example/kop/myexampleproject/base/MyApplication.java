package com.example.kop.myexampleproject.base;

import android.app.Application;
import com.blankj.utilcode.util.Utils;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/10/9 11:16
 */
public class MyApplication extends Application {

    private static MyApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication = this;
        Utils.init(this);

//        这是test1分支提交的代码
    }

    public static MyApplication getApplication() {
        return sMyApplication;
    }
}
