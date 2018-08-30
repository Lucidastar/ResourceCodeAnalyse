package com.lucidastar.glidestudy.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by qiuyouzone on 2018/8/29.
 */

@GlideModule
public class LoaderModel extends AppGlideModule{
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"image_catch",150000000));
//        super.applyOptions(context, builder);
    }

}
