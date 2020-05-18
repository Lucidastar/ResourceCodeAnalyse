package com.lucidastar.glidestudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mine.lucidastarutils.log.KLog;

/**
 * @author hyli
 * description：
 * @date 2020/5/18 10:15
 */
public class SupportFragment extends Fragment {

    private static final String TAG = "SupportFragment";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KLog.d(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.d(TAG,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.d(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        KLog.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.d(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.d(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.d(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KLog.d(TAG,"onDetach");
    }
}
