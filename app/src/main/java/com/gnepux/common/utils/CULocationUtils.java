package com.gnepux.common.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/**
 * Location相关辅助类
 * Created by Gnepux on 2015/10/20.
 */
public class CULocationUtils {

    private static final String TAG = CULocationUtils.class.getSimpleName();

    /**
     * 获取设备当前GPS状态（是否开启）
     *
     * @param context
     * @return true(开启) or false(关闭)
     */
    public static boolean getGpsStatus(Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(context.LOCATION_SERVICE);

        // getting GPS status
        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        System.out.println("gps band chhe " + isGPSEnabled);
        return isGPSEnabled;
    }

    /**
     * 获取设备当前Location实例
     *
     * @param context
     * @return
     */
    public static Location getCurrentLocation(Context context) {

        Location location = null;

        try {

            LocationManager locationManager = (LocationManager) context
                    .getSystemService(context.LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // please enable your location or connect to cellular network
            } else {
                if (isNetworkEnabled) {
                    Log.d(TAG, "Network");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        Log.d(TAG, "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     * 获取纬度
     *
     * @param context
     */
    public static double getLatitude(Context context) {
        Location location = getCurrentLocation(context);
        if (location != null) {
            return location.getLatitude();
        }
        return 0;
    }

    /**
     * 获取经度
     *
     * @param context
     */
    public static double getLongitude(Context context) {
        Location location = getCurrentLocation(context);
        if (location != null) {
            return location.getLongitude();
        }
        return 0;
    }

}
