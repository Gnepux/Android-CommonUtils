package com.gnepux.common.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 常用单位转换的辅助类
 * Created by Gnepux on 2015/10/20.
 */
public class CUDensityUtils {

    /**
     * dp转px
     *
     * @param mContext
     * @param dpVal
     * @return
     */
    public static int dp2px(Context mContext, float dpVal) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param mContext
     * @param spVal
     * @return
     */
    public static int sp2px(Context mContext, float spVal) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, mContext.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param mContext
     * @param pxVal
     * @return
     */
    public static float px2dp(Context mContext, float pxVal) {
        final  float scale = mContext.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param mContext
     * @param pxVal
     * @return
     */
    public static float px2sp(Context mContext, float pxVal) {
        return (pxVal / mContext.getResources().getDisplayMetrics().scaledDensity);
    }

}
