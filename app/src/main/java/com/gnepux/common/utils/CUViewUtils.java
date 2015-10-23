package com.gnepux.common.utils;

import android.os.Handler;
import android.view.View;

/**
 * View相关辅助类
 * Created by Gnepux on 2015/10/22.
 */
public class CUViewUtils {

    /**
     * 设置在minClickInterval的时间间隔内View不能双击
     *
     * @param view
     * @param minClickInterval
     */
    public static void preventDoubleClick(final View view, long minClickInterval) {
        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, minClickInterval);
    }

}
