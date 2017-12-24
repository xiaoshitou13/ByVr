package com.example.asus.byvr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.byvr.R;
import com.example.asus.byvr.activity.BaseFragment;
import com.example.asus.byvr.activity.VrImageActivity;
import com.example.asus.byvr.adater.QuanAdater;
import com.example.asus.byvr.bean.ImageItem;
import com.example.asus.byvr.bean.Shijian1;
import com.example.asus.byvr.utils.ImageGets;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class QuanJingtuFragment extends BaseFragment {


    private RecyclerView mQrecy;
    private SwipeRefreshLayout mSwipe;


    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_jingtu,container,false);
    }

    @Override
    protected void InitFragment(@NonNull final View itemView) {
        EventBus.getDefault().register(this);
        mQrecy = (RecyclerView) itemView.findViewById(R.id.qrecy);
        mSwipe = (SwipeRefreshLayout) itemView.findViewById(R.id.swipe);

        mQrecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipe.setRefreshing(false);
                    }
                },2000);
            }
        });
    }




    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Quanjing(Shijian1 shijian1){
        final List<ImageItem> jihe = shijian1.getJihe();

        QuanAdater quanAdater = new QuanAdater(jihe,getContext());
        mQrecy.setAdapter(quanAdater);

        quanAdater.setOnclickLinster(new QuanAdater.OnclickLinster() {
            @Override
            public void OnItemClickLinster(View v, int pos) {
                EventBus.getDefault().postSticky(new ImageItem(jihe.get(pos).title,jihe.get(pos).getUrl(),jihe.get(pos).getMap3()));
                getActivity().startActivity(new Intent(getContext(), VrImageActivity.class));
            }
        });
    }


}