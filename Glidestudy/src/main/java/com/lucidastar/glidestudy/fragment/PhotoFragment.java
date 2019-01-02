package com.lucidastar.glidestudy.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lucidastar.glidestudy.GlideImageLoader;
import com.lucidastar.glidestudy.GlideImageView;
import com.lucidastar.glidestudy.R;
import com.lucidastar.glidestudy.progress.OnProgressListener;
import com.lucidastar.glidestudy.progress.ProgressManager;
import com.lucidastar.glidestudy.view.CircleProgressView;
import com.mine.lucidastarutils.log.KLog;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends LazyLoadBaseFragment {

    private String mPhotoUrl;
    private ImageView mIvTest;
    private GlideImageView mGlideImageView;
    private int mPosition;
    private CircleProgressView mCircleProgressView;
    public PhotoFragment() {

    }

    public static PhotoFragment getInstance(){
        PhotoFragment photoFragment = new PhotoFragment();
        return photoFragment;
    }
    public static PhotoFragment getInstance(String url){
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("photoUrl",url);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }
    public static PhotoFragment getInstance(String url,int position){
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("photoUrl",url);
        bundle.putInt("position",position);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            mPhotoUrl = arguments.getString("photoUrl");
            mPosition = arguments.getInt("position");
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView(View rootView) {
        mIvTest = rootView.findViewById(R.id.iv_test);
        mGlideImageView = rootView.findViewById(R.id.giv_test);
        mCircleProgressView = rootView.findViewById(R.id.cpv_progress);
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户可见");
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户不可见");
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户第一次可见");
//        Glide.with(this).load(mPhotoUrl).into(mIvTest);


//        GlideImageLoader.create(mIvTest).listener(mPhotoUrl, new OnProgressListener() {
//            @Override
//            public void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes) {
//                KLog.i(getClass().getSimpleName() + percentage);
//            }
//        });
        mCircleProgressView.setVisibility(View.VISIBLE);
        mGlideImageView.diskCacheStrategy(DiskCacheStrategy.NONE).load(mPhotoUrl, R.drawable.ic_launcher_background, new OnProgressListener() {
            @Override
            public void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes) {
                KLog.i(getClass().getSimpleName() + percentage+"===bytesRead:"+bytesRead +"==totalBytes:"+totalBytes);
                mCircleProgressView.setProgress(percentage);
                if (isComplete){
                    mCircleProgressView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
