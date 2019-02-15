package com.lucidastar.glidestudy.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.mine.lucidastarutils.log.KLog;

/**
 * Created by qiuyouzone on 2019/2/15.
 */

public class MoveViewPager extends ViewPager{
    ViewDragHelper mViewDragHelper;
    GestureDetector mGestureDetector;
    private static final String TAG = "MoveViewPager";
    public MoveViewPager(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MoveViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return false;
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        KLog.i(getClass().getSimpleName() + "======"+y);
        return super.onTouchEvent(ev);
    }


}
