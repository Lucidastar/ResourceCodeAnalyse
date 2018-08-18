package com.lucidastar.analyse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lucidastar.analyse.event.UserEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class FirstActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        EventBus.getDefault().register(this);
        mTextView = this.findViewById(R.id.tv_test);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateUi(UserEvent userEvent) {
        Log.i("mine", "updateUi: " + userEvent.getName());
        mTextView.setText(userEvent.getName());
    }
}
