package com.gnepux.common.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;

/**
 * 设置相关辅助类
 * Created by Gnepux on 2015/10/23.
 */
public class CUSettingUtils {

    /**
     * 将device volumen设为app volumen
     *
     * @param mContext
     */
    public static void setCurrentDeviceVolume(Context mContext) {
        AudioManager audioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }


    /**
     * 切换手机的场景"静音" or "震动" or "正常"
     * Changes mobile profile to "Silent" or "Vibrate" or "Normal" mode
     *
     * @param context
     * @param mode    types of mode  - "0- Silent"
     *                - "1 - Vibrate"
     *                - "2 - Normal"
     */
    public static void chooseProfile(Context context, int mode) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mode == 0)
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        else if (mode == 1)
            audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        else if (mode == 2)
            audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    /**
     * 打开/关闭 bluetooth
     *
     * @param action
     */
    public static void onBlueTooth(String action) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
            }
        }
    }

    /**
     * 判断Bluetooth是否开启
     *
     * @param mContext
     * @return
     */
    public static boolean isBlueToothOn(Context mContext) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }

    /**
     * 打开/关闭 wifi
     *
     * @param mContext
     * @param action
     */
    public static void onWifi(Context mContext, String action) {
        WifiManager wm = ((WifiManager) mContext
                .getSystemService(Context.WIFI_SERVICE));
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!wm.isWifiEnabled()) {
                wm.setWifiEnabled(true);
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (wm.isWifiEnabled()) {
                wm.setWifiEnabled(false);
            }
        }
    }

    /**
     * 判断wifi是否开启
     *
     * @param mContext
     * @return
     */
    public static boolean isWifiOn(Context mContext) {
        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        return wm.isWifiEnabled();
    }

}
