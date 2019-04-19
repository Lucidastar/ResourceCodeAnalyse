package com.lucidastar.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnOutsidePhotoTapListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.OnSingleFlingListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.mine.lucidastarutils.log.KLog;

public class PhotoViewAnalysisActivity extends AppCompatActivity implements OnPhotoTapListener, View.OnClickListener, View.OnLongClickListener, OnOutsidePhotoTapListener, OnScaleChangedListener, OnSingleFlingListener {

    private PhotoView mPhotoView;
    private FrameLayout mFlContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_analysis);
//        mPhotoView = findViewById(R.id.pv_analysis);
        mFlContainer = findViewById(R.id.fl_contain);
        initData();
    }

    private void initData() {
        mFlContainer.removeAllViews();
        mPhotoView = new PhotoView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        mPhotoView.setLayoutParams(layoutParams);
        mFlContainer.addView(mPhotoView);
        Glide.with(this).load("http://pic3.16pic.com/00/03/88/16pic_388730_b.jpg").into(mPhotoView);
//        mPhotoView.setScale(3.0f,true);
        initListener();
        KLog.i("加载了");
    }

    private void initListener() {
        mPhotoView.setOnPhotoTapListener(this);
//        mPhotoView.setOnClickListener(this);
        mPhotoView.setOnLongClickListener(this);
        mPhotoView.setOnOutsidePhotoTapListener(this);
        mPhotoView.setOnScaleChangeListener(this);
        mPhotoView.setOnSingleFlingListener(this);

    }


    @Override
    public void onPhotoTap(ImageView view, float x, float y) {
        KLog.i("onPhotoTap===>x的值:"+x+"---y的值："+y);
    }

    @Override
    public void onClick(View v) {
        KLog.i("onClick");
    }


    @Override
    public boolean onLongClick(View v) {
        KLog.i("onLongClick");
        return true;
    }

    @Override
    public void onOutsidePhotoTap(ImageView imageView) {
        KLog.i("onOutsidePhotoTap");
    }

    @Override
    public void onScaleChange(float scaleFactor, float focusX, float focusY) {
//        KLog.i("onScaleChange");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        KLog.i("onFling");
        return true;
    }
}
