package com.gnepux.common.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 设备辅助类
 * Created by Gnepux on 2015/10/20.
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 */
public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();

    private static final String FILE_MEMORY = "/proc/meminfo";

    private static final String FILE_CPU = "/proc/cpuinfo";

    /**
     * 获取设备IMEI
     *
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        // check if has the permission
        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager()
                .checkPermission(Manifest.permission.READ_PHONE_STATE,
                        context.getPackageName())) {
            return manager.getDeviceId();
        } else {
            return null;
        }
    }

    /**
     * 获取mac地址
     *
     * @return
     */
    public static String getMacAddress(Context context)
    {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAdress = info.getMacAddress(); //获取mac地址
        return macAdress;
    }


    /**
     * 获取手机类型，NONE、GSM、CDMA、SIP
     * PHONE_TYPE_NONE.NONE、
     * PHONE_TYPE_NONE.GSM、
     * PHONE_TYPE_NONE.CDMA、
     * PHONE_TYPE_NONE.SIP
     *
     * @param context
     * @return
     */
    public static int getPhoneType(Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getPhoneType();
    }

    /**
     * 获取设备系统版本
     *
     * @return
     */
    public static int getSysVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取total memory , in MB
     *
     * @param context
     * @return
     */
    public static long getTotalMem(Context context) {
        try {
            FileReader fr = new FileReader(FILE_MEMORY);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split("\\s+");
            Log.w(TAG, text);
            return Long.valueOf(array[1]) / 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取free memory, in MB
     *
     * @param context
     * @return
     */
    public static long getFreeMem(Context context) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();

        manager.getMemoryInfo(info);
        long free = info.availMem / 1024 / 1024;
        return free;
    }

    /**
     * 获取CPU型号
     *
     * @return
     */
    public static String getCpuInfo() {
        try {
            FileReader fr = new FileReader(FILE_CPU);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
                Log.w(TAG, " .....  " + array[i]);
            }
            Log.w(TAG, text);
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取设备生产商
     *
     * @return
     */
    public static String getProductName() {
        return Build.PRODUCT;
    }

    /**
     * 获取设备型号
     *
     * @return
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * 获取设备序列号
     *
     * @return
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    /**
     * 获取设备硬件制造商
     *
     * @return
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    /**
     * 让屏幕保持常亮
     *
     * @param context
     */
    public static void disableSleepMode(Context context) {
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 不使屏幕保持常亮
     *
     * @param context
     */
    public static void enableSleepMode(Context context) {
        ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 获得屏幕高
     *
     * @param context
     * @return
     */
    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获得屏幕宽
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
