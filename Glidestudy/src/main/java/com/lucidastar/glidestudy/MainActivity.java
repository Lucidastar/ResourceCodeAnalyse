package com.lucidastar.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mine.lucidastarutils.log.KLog;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvTest;
//    private String imageUrl = "http://d.hiphotos.baidu.com/image/pic/item/024f78f0f736afc3a2a61a56be19ebc4b745125e.jpg";
    private String imageUrl = "http://fileserver.qiuyouzone.com/fileserver/get/f29ac45c01fd4071a5422bc7906c715b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvTest = findViewById(R.id.iv_test);
    }

    public void getPic(View view) {
//        Glide.with(this).asGif().load(R.drawable.icon_insert_ticket).into(mIvTest);
        KLog.i("测试一下");
        try {
            InputStream icon_inset_ticket = getAssets().open("icon_insert_ticket.gif");
            Glide.with(MainActivity.this).asGif().load("file:///android_asset/icon_insert_ticket.gif").into(mIvTest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
