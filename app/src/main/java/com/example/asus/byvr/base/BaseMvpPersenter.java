package com.example.asus.byvr.base;

import com.example.asus.byvr.model.Imodel;
import com.example.asus.byvr.model.Mymodel;
import com.example.asus.byvr.view.Iview;

/**
 * Created by ASUS on 2017/12/21.
 */

public interface BaseMvpPersenter<V extends Iview,M extends Mymodel> {
    void Attch(V v,M m);  //e
    void Jiebang();
}
