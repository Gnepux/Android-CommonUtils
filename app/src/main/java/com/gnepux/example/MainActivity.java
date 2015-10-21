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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gnepux.common.utils.AppUtils;
import com.gnepux.common.utils.DeviceUtils;
import com.gnepux.common.utils.ImageUtils;
import com.gnepux.common.utils.NotificationUtils;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "CommonUtils";

    private static final String IMG_PATH = "/sdcard/DCIM/Screenshots/sample.png";

    private static final String VIDEO_PATH = "/sdcard/DCIM/Camera/video.mp4";

    private static final String IMG_URL = "http://image5.suning.cn/uimg/cms/img/143882327432832878.jpg";

    EditText editText;

    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edittext);
        imageview = (ImageView) findViewById(R.id.imageview);
    }

    public void goCheckIsEmpty(View v) {
        ImageUtils.downloadImageFromURL(IMG_URL , imageview);
    }

    public void goClean(View v) {
        ImageUtils.openVideo(this, VIDEO_PATH);
    }


}
