/*
package com.lucidastar.glidestudy.glideload;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

*/
/**
 * Created by qiuyouzone on 2018/8/29.
 *//*


@GlideModule
public class LoaderModel extends AppGlideModule{
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"image_catch",150000000));
//        super.applyOptions(context, builder);

    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
//        // 写入咱们的okhttp
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        // 写入咱们的okhttp的拦截器,在拦截器中监听进度
//        builder.addInterceptor(new ProgressInterceptor());
//        OkHttpClient okHttpClient = builder.build();
//        // glide吧urlConnection替换为okhttp
//        registry.replace(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));

    }
}
*/
