package com.example.asus.byvr.view;

import android.support.v7.view.menu.MenuView;

import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.utils.ImageGets;

import java.util.List;

/**
 * Created by ASUS on 2017/12/21.
 */

public interface Iview {
     void Image(List<ImageItem> imageItems);
     void Video(List<Datas> datas);
}
