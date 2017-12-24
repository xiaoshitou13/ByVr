package com.example.asus.byvr.bean;

/**
 * Created by ASUS on 2017/12/22.
 */

public class Videobean {
    String name;
    String img;
    String play;

    public Videobean(String name, String img, String play) {
        this.name = name;
        this.img = img;
        this.play = play;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }
}
