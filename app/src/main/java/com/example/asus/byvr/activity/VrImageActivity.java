package com.example.asus.byvr.activity;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.asus.byvr.R;
import com.example.asus.byvr.base.BaseActivity;
import com.example.asus.byvr.base.BaseMVPActivity;
import com.example.asus.byvr.bean.Datas;
import com.example.asus.byvr.bean.ImageItem;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class VrImageActivity extends BaseActivity{

    private VrPanoramaView mVrpanor;
    private ProgressBar mProgressBar;
    private MediaPlayer player;




    @Override
    protected int getLayout() {
        return R.layout.activity_vr_image;
    }

    @Override
    protected void initViewimage() {
        EventBus.getDefault().register(this);

        mVrpanor = (VrPanoramaView) findViewById(R.id.vrpanor);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void VrImageEvent(ImageItem imageGets) {

        initMusic(imageGets.getMap3());

        OkGo.get(imageGets.getUrl()).cacheKey(imageGets.getUrl()).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {
                mProgressBar.setVisibility(View.GONE);
                VrPanoramaView.Options options = new VrPanoramaView.Options();

                options.inputType = VrPanoramaView.Options.TYPE_MONO;

                mVrpanor.loadImageFromBitmap(bitmap, options);
            }
        });
    }

    private void initMusic(String map3) {
        if(map3!=null){
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {
                player.setDataSource(this, Uri.parse(map3));

                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVrpanor.pauseRendering();
        if(player != null){
            player.pause();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        mVrpanor.resumeRendering();
        if(player!=null){
            player.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // EventBus.getDefault().unregister(this);
        mVrpanor.shutdown();
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
    }



}
