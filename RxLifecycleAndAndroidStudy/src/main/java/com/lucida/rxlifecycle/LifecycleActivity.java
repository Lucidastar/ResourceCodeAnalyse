package com.lucida.rxlifecycle;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mine.lucidastarutils.utils.ToastUtils;
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

    Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(LifecycleActivity.this,"hello",Toast.LENGTH_SHORT).show();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // Specifically bind this until onPause()
        Observable.just("1","1")
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "Unsubscribing subscription from onCreate()");
                    }
                })
                .compose(this.<String>bindToLifecycle())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String aLong) {
                        Log.i(TAG, "result"+aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        testThread();
                    }
                });
                /*.subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.i(TAG, "Started in onCreate(), running until onPause(): " + num);
                        testThread();
                    }
                });*/


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

    private void testThread(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        mHandler.sendMessage(Message.obtain());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
