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

import com.example.asus.byvr.R;
import com.example.asus.byvr.activity.BaseFragment;
import com.example.asus.byvr.activity.VrImageActivity;
import com.example.asus.byvr.activity.VrVideoActivity;
import com.example.asus.byvr.adater.VideoAdataer;
import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.Videobean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class VrVideoFragment extends BaseFragment {


    private RecyclerView mRecycle;
    private SwipeRefreshLayout mSwipevideo;

    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vr_video, container, false);
    }

    @Override
    protected void InitFragment(View fragmentLayout) {
        EventBus.getDefault().register(this);
        mRecycle = (RecyclerView) fragmentLayout.findViewById(R.id.viderecy);
        mSwipevideo = (SwipeRefreshLayout) fragmentLayout.findViewById(R.id.videoswipe);

        mRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        mSwipevideo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipevideo.setRefreshing(false);
                    }
                },2000);
            }
        });
    }




    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void VideoPlayer(final List<Datas> datas){
        VideoAdataer videoAdataer = new VideoAdataer(datas,getContext());
        mRecycle.setAdapter(videoAdataer);

        videoAdataer.setOnclickLinster(new VideoAdataer.OnclickLinsters() {
            @Override
            public void OnItemClickLinster(View v, int pos) {
                EventBus.getDefault().postSticky(new Videobean(datas.get(0).getContent().get(pos).getTitle(),datas.get(0).getContent().get(pos).getImg(),datas.get(0).getContent().get(pos).getPlay()));
                getActivity().startActivity(new Intent(getContext(), VrVideoActivity.class));

            }
        });
    }


}