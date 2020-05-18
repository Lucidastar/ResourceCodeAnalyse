package com.lucidastar.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lucidastar.glidestudy.fragment.SupportFragment;
import com.mine.lucidastarutils.log.KLog;

import java.util.Objects;

public class LifecycleCheckActivity extends AppCompatActivity {

    static final String FRAGMENT_TAG = "LUCIDA_TAG";
    private static final String TAG = "mine";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_check);
    }

    private void checkLifecycleMethod(){

        getSupportFragmentManager().beginTransaction().add(new SupportFragment(),FRAGMENT_TAG).commitAllowingStateLoss();


    }

    public void onClickLifecycle(View view) {
        KLog.d();
        checkLifecycleMethod();
    }
}
