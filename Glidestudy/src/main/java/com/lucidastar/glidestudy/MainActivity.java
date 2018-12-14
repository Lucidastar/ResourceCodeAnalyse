package com.lucidastar.glidestudy;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lucidastar.glidestudy.fragment.PhotoFragment;
import com.mine.lucidastarutils.log.KLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvTest;
    ViewPager mViewPager;
//    private String imageUrl = "http://d.hiphotos.baidu.com/image/pic/item/024f78f0f736afc3a2a61a56be19ebc4b745125e.jpg";
    private String imageUrl = "http://fileserver.qiuyouzone.com/fileserver/get/f29ac45c01fd4071a5422bc7906c715b";
    private String[] mPhotoUrlStr = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321321034&di=73f787e06ea852782d6d336799787635&imgtype=0&src=http%3A%2F%2Ffa.topitme.com%2Fa%2Fa2%2F8a%2F1130147841d058aa2al.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381382&di=60700e109fdf38c7eeb8129fd4abbc38&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0b46f21fbe096b63b8f6858406338744eaf8ac73.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381382&di=526e298791a3349bdc479a6836625887&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F99%2F17%2F90q58PICeia.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381381&di=9d0ff97809ca30987ee2ded80feb4934&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F39%2F42%2F35958PICHZw.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381381&di=10201135cda5dddb7af5a6116f6ea806&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F27%2F08%2F09j58PICarX.jpg"
    };
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvTest = findViewById(R.id.iv_test);
        mViewPager = findViewById(R.id.vp_test);
        initData();

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

    private void initData() {
        int i = 0;
        for (String url : mPhotoUrlStr) {
            mFragments.add(PhotoFragment.getInstance(url,i++));
        }
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
