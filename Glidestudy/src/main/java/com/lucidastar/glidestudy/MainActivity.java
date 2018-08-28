package com.lucidastar.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvTest;
    private String imageUrl = "http://d.hiphotos.baidu.com/image/pic/item/024f78f0f736afc3a2a61a56be19ebc4b745125e.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvTest = findViewById(R.id.iv_test);
    }

    public void getPic(View view) {
        Glide.with(this).load(imageUrl).into(mIvTest);
    }
}
