package com.gnepux.example;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gnepux.common.utils.CUImageUtils;
import com.gnepux.common.utils.CUKeyBoardUtils;
import com.gnepux.common.utils.CUScreenUtils;
import com.gnepux.common.utils.T;

import java.io.FileNotFoundException;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "CommonUtils";

    private static final String IMG_PATH = "/sdcard/miui/wallpaper/yellow.jpg";

    private static final String VIDEO_PATH = "/sdcard/DCIM/Camera/video.mp4";

    private static final String IMG_URL = "http://image5.suning.cn/uimg/cms/img/143882327432832878.jpg";

    //EditText editText;

    ImageView imageview;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // editText = (EditText) findViewById(R.id.edittext);
        imageview = (ImageView) findViewById(R.id.imageview);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageview.setImageBitmap(CUImageUtils.getRoundedCornerBitmap(CUImageUtils.getBitmap(IMG_PATH), progress));
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
        CUKeyBoardUtils.closeKeyboard(this);
    }

    public void goClean(View v) {
        T.show(this, String.valueOf(CUKeyBoardUtils.isActive(this)), Toast.LENGTH_SHORT);
    }


}
