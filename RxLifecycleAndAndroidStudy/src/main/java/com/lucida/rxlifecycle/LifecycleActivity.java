package com.lucida.rxlifecycle;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class LifecycleActivity extends RxAppCompatActivity {

    private static final String TAG = "mine";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // Specifically bind this until onPause()
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "Unsubscribing subscription from onCreate()");
                    }
                })
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.i(TAG, "Started in onCreate(), running until onPause(): " + num);
                    }
                });


    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart()");

        // Using automatic unsubscription, this should determine that the correct time to
        // unsubscribe is onStop (the opposite of onStart).
        /*Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "Unsubscribing subscription from onStart()");
                    }
                })
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.i(TAG, "Started in onStart(), running until in onStop(): " + num);
                    }
                });*/
    }

}
