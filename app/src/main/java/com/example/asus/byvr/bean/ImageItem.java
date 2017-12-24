package com.example.asus.byvr.bean;

/**
 * Created by ASUS on 2017/12/21.
 */

public class ImageItem {
    //标题
    public String title;
    //图片的url
    public String url;
    //音乐,也是一个网址url
    public String map3;


    public ImageItem(String title, String url, String map3) {
        this.title = title;
        this.url = url;
        this.map3 = map3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMap3() {
        return map3;
    }

    public void setMap3(String map3) {
        this.map3 = map3;
    }
}
