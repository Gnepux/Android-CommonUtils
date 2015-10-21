package com.gnepux.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 地图辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class MapUtils {

    /**
     * 在地图上显示地点
     *
     * @param mContext
     * @param address  to show on map.
     */
    public static void showAddressOnMap(Context mContext, String address) {
        address = address.replace(' ', '+');
        Intent geoIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + address));
        mContext.startActivity(geoIntent);
    }

}
