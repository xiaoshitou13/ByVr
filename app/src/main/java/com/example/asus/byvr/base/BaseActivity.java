package com.example.asus.byvr.base;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.byvr.R;
import com.example.asus.byvr.bean.Shijian1;
import com.example.asus.byvr.fragment.QuanJingtuFragment;
import com.example.asus.byvr.fragment.VrVideoFragment;
import com.example.asus.byvr.utils.ImageGets;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ASUS on 2017/12/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private VrPanoramaView mVrpanor;
    private ProgressBar mProgressBar;
    private MediaPlayer player;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());


        initView();

        initViewimage();

        initvideos();

    }

    protected void initvideos() {
    };

    protected  void initViewimage(){};

    protected  void initView(){};


    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
