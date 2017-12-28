package com.example.asus.byvr.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.asus.byvr.R;
import com.example.asus.byvr.base.BaseActivity;
import com.example.asus.byvr.bean.Videobean;
import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

public class VrVideoActivity extends BaseActivity {

    private VrVideoView mVrvideo;
    private AppCompatSeekBar mBarSeek;
    private TextView mProgressTv;
    private VideoLoadTask mVideoLoadTask;




    @Override
    protected int getLayout() {
        return R.layout.activity_vr_video;
    }


    @Override
    protected void initvideos() {
        EventBus.getDefault().register(this);

        mVrvideo = (VrVideoView) findViewById(R.id.vrvideo);
        mBarSeek = (AppCompatSeekBar) findViewById(R.id.seek_bar);
        mProgressTv = (TextView) findViewById(R.id.tv_progress);


        mVrvideo.setInfoButtonEnabled(false);
        mVrvideo.setDisplayMode(VrVideoView.DisplayMode.FULLSCREEN_STEREO);

        mVrvideo.setEventListener(new VrVideoEventListener());


    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Player(Videobean videobean){
        mVideoLoadTask = new VideoLoadTask();
        mVideoLoadTask.execute(videobean.getPlay());
    }


    private class  VideoLoadTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            VrVideoView.Options options = new VrVideoView.Options();

            options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;

            options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;

            try {
                mVrvideo.loadVideo(Uri.parse(strings[0]),options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mVrvideo.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVrvideo.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mVrvideo.shutdown();

        if(mVideoLoadTask!=null){
            if(!mVideoLoadTask.isCancelled()){
                mVideoLoadTask.cancel(true);
            }
        }
    }

    private class MyEventLinter extends VrVideoEventListener{

        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();

            long max = mVrvideo.getDuration();

            mBarSeek.setMax((int) max);
        }

        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
            Toast.makeText(VrVideoActivity.this, "安装失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNewFrame() {
            super.onNewFrame();
            int currenpostion = (int) mVrvideo.getCurrentPosition();

            mBarSeek.setProgress(currenpostion);

            mProgressTv.setText("播放进度:"+String.format("%.2f",currenpostion/1000.f));
        }

        @Override
        public void onCompletion() {
            super.onCompletion();
            mVrvideo.seekTo(0);
            mVrvideo.pauseVideo();
            mBarSeek.setProgress(0);
            isPaused = false;
        }
        //设置一个视频播放状态的标签
        private boolean isPaused  = true;

        @Override
        public void onClick() {
            super.onClick();

            if(isPaused){
                mVrvideo.pauseVideo();
            }else{
                mVrvideo.playVideo();
            }
            isPaused = !isPaused;
        }
    }
}
