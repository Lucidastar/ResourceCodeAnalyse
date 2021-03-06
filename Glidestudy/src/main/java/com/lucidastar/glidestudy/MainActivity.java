package com.lucidastar.glidestudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getPic(View view) {
        startActivity(new Intent(this,PictureActivity.class));
    }


    public void getPhotoActivity(View view) {
        startActivity(new Intent(this,PhotoViewAnalysisActivity.class));
    }

    public void getNoUIFragmentLifecycle(View view) {
        startActivity(new Intent(this,LifecycleCheckActivity.class));
    }
}
