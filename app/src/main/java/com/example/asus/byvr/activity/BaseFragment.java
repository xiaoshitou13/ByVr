package com.example.asus.byvr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ASUS on 2017/12/24.
 */

public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentLayout = getFragmentLayout(inflater, container, savedInstanceState);
        InitFragment(fragmentLayout);
        return fragmentLayout;
    }

    protected abstract void InitFragment(View fragmentLayout);

    protected abstract View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
