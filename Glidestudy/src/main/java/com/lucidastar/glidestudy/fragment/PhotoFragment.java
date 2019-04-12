package com.lucidastar.glidestudy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.lucidastar.glidestudy.R;
import com.lucidastar.glidestudy.view.CircleProgressView;
import com.mine.lucidastarutils.log.KLog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * https://github.com/wasabeef/glide-transformations
 * https://github.com/VicJcc/BrowseImg/blob/master/app/build.gradle
 */
public class PhotoFragment extends LazyLoadBaseFragment {

    private String mPhotoUrl;
    private PhotoView mPhotoView;
    private int mPosition;
    int i = 0;
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
        mPhotoView = rootView.findViewById(R.id.photo_view);
        mCircleProgressView = rootView.findViewById(R.id.cpv_progress);


    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
//        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户可见");
//        if (i >= 100){
//            mCircleProgressView.setVisibility(View.GONE);
//        }else {
//            mCircleProgressView.setVisibility(View.VISIBLE);
//        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                while (i < 100){
                    try {
                        Thread.sleep(200);
                        i ++;
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (i == 99){
//                                    mCircleProgressView.setVisibility(View.GONE);
//                                }
//                                mCircleProgressView.setProgress(i);
//                            }
//                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },100);

        initListener();
    }

    private void initListener() {

    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
//        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户不可见");
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
//        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户第一次可见");
        Glide.with(this).load(mPhotoUrl).into(mPhotoView);

    }

}
