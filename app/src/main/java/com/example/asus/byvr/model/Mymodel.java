package com.example.asus.byvr.model;

import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.utils.Apiserviers;
import com.example.asus.byvr.utils.ImageGets;
import com.example.asus.byvr.view.Iview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/12/21.
 */

public class Mymodel implements Imodel {
    @Override
    public void ModelImage(final Iview iview) {
        iview.Image(ImageGets.getImageItems());

        OkGo.get(Apiserviers.URL_Query).cacheKey(Apiserviers.URL_Query).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Datas datas = new Gson().fromJson(s,Datas.class);
                List<Datas> datas1 = new ArrayList<>();
                datas1.add(datas);
                iview.Video(datas1);
            }
        });

    }
}
