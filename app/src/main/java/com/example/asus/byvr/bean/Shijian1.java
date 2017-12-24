package com.example.asus.byvr.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/12/21.
 */

public class Shijian1 {
    List<ImageItem> jihe;

    public Shijian1(List<ImageItem> jihe) {
        this.jihe = jihe;
    }

    public List<ImageItem> getJihe() {
        return jihe;
    }

    public void setJihe(List<ImageItem> jihe) {
        this.jihe = jihe;
    }
}
