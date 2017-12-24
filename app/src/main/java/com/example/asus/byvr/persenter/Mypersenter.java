package com.example.asus.byvr.persenter;

import com.example.asus.byvr.base.BasePersenter;
import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.model.Imodel;
import com.example.asus.byvr.model.Mymodel;
import com.example.asus.byvr.utils.ImageGets;
import com.example.asus.byvr.view.Iview;

import java.util.List;

/**
 * Created by ASUS on 2017/12/21.
 */

public class Mypersenter extends BasePersenter<Iview,Mymodel>{

    public void ImagePersenter(){
        m1.ModelImage(new Iview() {
            @Override
            public void Image(List<ImageItem> imageItems) {
                v1.Image(imageItems);
            }

            @Override
            public void Video(List<Datas> datas) {
                v1.Video(datas);
            }
        });

    }
}
