package com.lucidastar.analyse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lucidastar.analyse.event.UserEvent;
import com.lucidastar.analyse.utils.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTest;
    private Button mButton;
    private Button mButtonAsync;
    private Button mButtonOpenFirstActivity;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mButton = this.findViewById(R.id.btn_send);
        mButtonAsync = this.findViewById(R.id.btn_send_async);
        mTvTest = findViewById(R.id.tv_name);
        mButtonOpenFirstActivity = findViewById(R.id.btn_start_first);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new UserEvent("jerry", "20"));
            }
        });

        mButtonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "onClick: =========================================");
                Logger.i("点击了了  了");
                new Thread(){
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new UserEvent("john", "24"));
                    }
                }.start();

            }
        });

        mButtonOpenFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().removeAllStickyEvents();
                startActivity(new Intent(MainActivity.this,FirstActivity.class));
            }
        });

    }


    //主线程
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getUserInfoMain(UserEvent userEvent) {
//        Log.i(TAG, "getUserInfoMain: "+"名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge()+",是否是主线程"+ (Looper.myLooper() == Looper.getMainLooper()));
        mTvTest.setText("名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge());
    }
    //异步
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getUserInfoAsync(UserEvent userEvent) {
//        Log.i(TAG, "getUserInfoAsync: "+"名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge()+",是否是主线程"+ (Looper.myLooper() == Looper.getMainLooper()));

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getUserInfoBackground(UserEvent userEvent) {
//        Log.i(TAG, "getUserInfoBackground: "+"名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge()+",是否是主线程"+ (Looper.myLooper() == Looper.getMainLooper()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void getUserInfoMainOrdered(UserEvent userEvent) {

//        Log.i(TAG, "getUserInfoMainOrdered: "+"名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge()+",是否是主线程"+ (Looper.myLooper() == Looper.getMainLooper()));
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void getUserInfoPosting(UserEvent userEvent) {
//        Log.i(TAG, "getUserInfoPosting: "+"名字：" + userEvent.getName() + "====年龄：" + userEvent.getAge()+",是否是主线程"+ (Looper.myLooper() == Looper.getMainLooper()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
