package com.lucida.rxlifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class LifecycleActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
    }
}
