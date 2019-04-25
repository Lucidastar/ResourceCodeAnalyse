package com.lucidastar.glidestudy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lucidastar.glidestudy.fragment.PhotoFragment;
import com.lucidastar.glidestudy.view.MoveViewPager;

import java.util.ArrayList;
import java.util.List;

public class PictureActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    MyViewPagerAdapter mMyViewPagerAdapter;
    private TextView mTvNum;
    private FrameLayout mFlBack;
    private ImageView mIvDownLoad;
    private String[] photoList = new String[]{"http://img0.imgtn.bdimg.com/it/u=1531711675,3243572643&fm=200&gp=0.jpg",
            "http://pic3.16pic.com/00/03/88/16pic_388730_b.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1813833741,3890256991&fm=200&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3138169589,483146703&fm=200&gp=0.jpg",
            "http://pic33.photophoto.cn/20141128/0033034301829029_b.jpg",
            "http://img3.3lian.com/2013/c3/62/d/48.jpg"};
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        mViewPager = findViewById(R.id.vp_pic_list);
        mTvNum = findViewById(R.id.tv_num);
        mFlBack = findViewById(R.id.fl_back);
        mIvDownLoad = findViewById(R.id.iv_down_load);
        initData();
        initListener();
    }

    private void initListener() {
        mFlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PictureActivity.this,"下载",Toast.LENGTH_SHORT).show();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvNum.setText(position+1+"/"+ photoList.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mTvNum.setText("1/"+photoList.length);
        mViewPager.setOffscreenPageLimit(photoList.length);
        for (int i = 0; i < photoList.length; i++) {
            mFragments.add(PhotoFragment.getInstance(photoList[i]));
        }
        mMyViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mMyViewPagerAdapter);
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragmentList;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            mFragmentList = fragmentList;
        }

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
