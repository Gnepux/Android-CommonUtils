package com.gnepux.common.utils;

import android.content.Context;

import java.io.File;

/**
 * 文件辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class CUFileUtils {

    /**
     * 在指定目录下创建新目录
     *
     * @param mContext
     * @param path       location in which you have to create folder
     * @param folderName name of the folder
     */
    public static boolean createFolder(Context mContext, String path,
                                       String folderName) {
        File SDCardRoot = new File(path, folderName);
        if (!SDCardRoot.exists()) {
            return SDCardRoot.mkdir();
        }
//        else {
//            Toast.makeText(mContext, "fail", Toast.LENGTH_LONG).show();
//        }
        return false;
    }

    /**
     * 获取指定目录下某后缀文件的数量
     *
     * @param format        of file extensions like .jpg, .png, .mp3, .mp4
     * @param directoryPath location of the directory
     * @return give counter integer
     */
    public static int getFileCounts(String format, String directoryPath) {
        int Sdcardcount = 0;
        File fileCount = new File(directoryPath);
        if (fileCount.exists()) {
            File[] list = fileCount.listFiles();
            for (File f : list) {
                String name = f.getName();
                if (name.endsWith(format))
                    Sdcardcount++;
            }
        }
        return Sdcardcount;
    }

}
