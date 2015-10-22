package com.gnepux.example;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gnepux.common.utils.AppUtils;
import com.gnepux.common.utils.DeviceUtils;
import com.gnepux.common.utils.DialogUtils;
import com.gnepux.common.utils.ImageUtils;
import com.gnepux.common.utils.NotificationUtils;
import com.gnepux.common.utils.SDCardUtils;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "CommonUtils";

    private static final String IMG_PATH = "/sdcard/miui/wallpaper/yellow.jpg";

    private static final String VIDEO_PATH = "/sdcard/DCIM/Camera/video.mp4";

    private static final String IMG_URL = "http://image5.suning.cn/uimg/cms/img/143882327432832878.jpg";

    EditText editText;

    ImageView imageview;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edittext);
        imageview = (ImageView) findViewById(R.id.imageview);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageview.setImageBitmap(ImageUtils.getRoundedCornerBitmap(ImageUtils.getBitmap(IMG_PATH), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void goCheckIsEmpty(View v) {
        ImageUtils.downloadImageFromURL(IMG_URL , imageview);
    }

    public void goClean(View v) {
        imageview.setImageBitmap(ImageUtils.getRoundedCornerBitmap(ImageUtils.getBitmap(IMG_PATH), 50));
    }


}
