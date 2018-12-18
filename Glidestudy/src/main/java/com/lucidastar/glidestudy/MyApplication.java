package com.lucidastar.glidestudy;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
