package com.example.asus.byvr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.asus.byvr.R;
import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.model.Mymodel;
import com.example.asus.byvr.persenter.Mypersenter;
import com.example.asus.byvr.utils.ImageGets;
import com.example.asus.byvr.view.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ASUS on 2017/12/21.
 */

public abstract class BaseMVPActivity<P extends Mypersenter,M extends Mymodel> extends BaseActivity implements Iview{




    private Mypersenter mypersenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mypersenter = new Mypersenter();
        mypersenter.Attch(this,new Mymodel());

        mypersenter.ImagePersenter();


        super.onCreate(savedInstanceState);


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mypersenter.Jiebang();
    }
}
