package com.example.asus.byvr.base;

import com.example.asus.byvr.model.Imodel;
import com.example.asus.byvr.model.Mymodel;
import com.example.asus.byvr.view.Iview;

import java.lang.ref.WeakReference;

/**
 * Created by ASUS on 2017/12/21.
 */

public abstract class BasePersenter<V extends Iview,M extends Mymodel> implements BaseMvpPersenter<V,M> {


    protected WeakReference<V> weakReference ;
    protected WeakReference<M> mWeakReference;
    protected M m1;
    protected V v1;

    @Override
    public void Attch(V v,M m) {
           weakReference = new WeakReference<V>(v);

           mWeakReference = new WeakReference<M>(m);


          v1 = weakReference.get();
          m1 = mWeakReference.get();
    }

    @Override
    public void Jiebang() {
         if(weakReference!=null){
             weakReference.clear();
             weakReference = null;
         }else if(mWeakReference!=null){
             mWeakReference.clear();
             mWeakReference = null;
         }
    }
}
