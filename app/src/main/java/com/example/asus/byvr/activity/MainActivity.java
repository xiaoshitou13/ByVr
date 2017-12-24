package com.example.asus.byvr.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.byvr.R;
import com.example.asus.byvr.base.BaseActivity;
import com.example.asus.byvr.base.BaseMVPActivity;
import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.bean.Shijian1;
import com.example.asus.byvr.fragment.QuanJingtuFragment;
import com.example.asus.byvr.fragment.VrVideoFragment;
import com.example.asus.byvr.utils.ImageGets;
import com.example.asus.byvr.view.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends BaseMVPActivity{

    private DrawerLayout mDrawerLayout;
    private LinearLayout drawer_content;


    private ImageButton btn;
    private String[] title = new String[]{"全景图", "Vr视频"};
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }




    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_content = (LinearLayout) findViewById(R.id.drawer_content);
        btn = (ImageButton) findViewById(R.id.btn);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.vp);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(drawer_content);
            }
        });

        // 关闭手势滑动
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // 打开手势滑动
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // 关闭手势滑动
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        for (int i = 0; i < title.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title[i]));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {

                return title.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (title[position]) {
                    case "全景图":
                        fragment = new QuanJingtuFragment();
                        break;
                    case "Vr视频":
                        fragment = new VrVideoFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0){
                    //     EventBus.getDefault().postSticky(new Shijian1(ImageGets.getImageItems()));
                }else if(position == 1){
                    //     Toast.makeText(BaseActivity.this, "woshishiping", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void Image(List<ImageItem> imageItems) {
     //   Toast.makeText(this, ""+imageItems.get(0).getTitle(), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().postSticky(new Shijian1(imageItems));
    }

    @Override
    public void Video(List<Datas> datas) {

        EventBus.getDefault().postSticky(datas);
    }
}
