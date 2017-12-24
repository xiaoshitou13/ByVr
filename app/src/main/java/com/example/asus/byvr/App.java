package com.example.asus.byvr;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by ASUS on 2017/12/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);

        OkGo.getInstance().setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
            .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
            .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
            .setRetryCount(3);
     }
}
