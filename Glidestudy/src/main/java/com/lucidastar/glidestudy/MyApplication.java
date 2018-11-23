package com.lucidastar.glidestudy;

import android.app.Application;

import com.mine.lucidastarutils.utils.Utils;

/**
 * Created by qiuyouzone on 2018/11/23.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this,true,"Lucida");
    }
}
