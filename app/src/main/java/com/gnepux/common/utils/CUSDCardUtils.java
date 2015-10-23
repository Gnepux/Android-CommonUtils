package com.gnepux.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SD卡相关辅助类
 * Created by Gnepux on 2015/10/20.
 */
public class CUSDCardUtils {

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取SD卡的总容量 in byte
     *
     * @return
     */
    public static long getSDCardTotalSize() {
        if (isSDCardAvailable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();

            long totalSize = totalBlocks * blockSize;
            return totalSize;
        }
        return 0;
    }

    /**
     * 获取SD卡的可用容量 in byte
     *
     * @return
     */
    public static long getSDCardAvailSize() {
        if (isSDCardAvailable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();

            long availSize = availableBlocks * blockSize;
            return availSize;
        }
        return 0;
    }

    /**
     * 获取SD卡的总容量 Format in hunman.
     *
     * @return
     */
    public static String getSDCardTotalSizeInHuman(Context mContext) {
        if (isSDCardAvailable()) {
            long totalSize = getSDCardTotalSize();
            String formatTotalSize = Formatter.formatFileSize(mContext, totalSize);
        //    String FormatTotalSize = Formatter.formatShortFileSize(mContext, totalSize);   //formatShortFileSize 9.09G会显示成9.1G
            return formatTotalSize;
        }
        return "";
    }

    /**
     * 获取SD卡的可用容量 Format in hunman.
     *
     * @return
     */
    public static String getSDCardAvailSizeInHuman(Context mContext) {
        if (isSDCardAvailable()) {
            long availSize = getSDCardAvailSize();
            String formaAvailSize = Formatter.formatFileSize(mContext, availSize);
            //    String formaAvailSize = Formatter.formatShortFileSize(mContext, availSize);   //formatShortFileSize 9.09G会显示成9.1G
            return formaAvailSize;
        }
        return "";
    }

    /**
     * 获取机身的存储路径
     *
     * @return
     */
    public static String getSystemDataPath() {
        return Environment.getDataDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取机身的总容量 in byte
     *
     * @return
     */
    public static long getSystemTotalSize() {
        StatFs stat = new StatFs(getSystemDataPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();

        long totalSize = totalBlocks * blockSize;
        return totalSize;
    }

    /**
     * 获取机身的可用容量 in byte
     *
     * @return
     */
    public static long getSystemAvailSize() {
        StatFs stat = new StatFs(getSystemDataPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();

        long availSize = availableBlocks * blockSize;
        return availSize;
    }

    /**
     * 获取机身的总容量 Format in hunman.
     *
     * @return
     */
    public static String getSystemTotalSizeInHuman(Context mContext) {
        long totalSize = getSystemTotalSize();
        String formatTotalSize = Formatter.formatFileSize(mContext, totalSize);
        //    String FormatTotalSize = Formatter.formatShortFileSize(mContext, totalSize);   //formatShortFileSize 9.09G会显示成9.1G
        return formatTotalSize;
    }

    /**
     * 获取机身的可用容量 Format in hunman.
     *
     * @return
     */
    public static String getSystemAvailSizeInHuman(Context mContext) {
        long availSize = getSystemAvailSize();
        String formaAvailSize = Formatter.formatFileSize(mContext, availSize);
        //    String formaAvailSize = Formatter.formatShortFileSize(mContext, availSize);   //formatShortFileSize 9.09G会显示成9.1G
        return formaAvailSize;
    }

    /**
     * 判断某个文件是否存在于SDCard中
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean isFileExistInSDCard(String filePath, String fileName) {
        boolean flag = false;
        if (isSDCardAvailable()) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 向sdcard上写文件
     *
     * @param filePath
     * @param fileName
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean saveFileToSDCard(String filePath, String fileName, String content) throws IOException {
        boolean flag = false;
        if (isSDCardAvailable()) {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(filePath, fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
            flag = true;
        }
        return flag;
    }

    /**
     * 从sdcard上读文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static byte[] readFileFromSDCard(String filePath, String fileName) {
        byte[] buffer = null;
        try {
            if (isSDCardAvailable()) {
                String fileFullPath = filePath + File.separator + fileName;
                FileInputStream fin = new FileInputStream(fileFullPath);
                int length = fin.available();
                buffer = new byte[length];
                fin.read(buffer);
                fin.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 从sdcard上删除某路径的文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean deleteFileFromSDCard(String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        if (file == null || !file.exists() || file.isDirectory()) {
            return false;
        }
        return file.delete();
    }

}
