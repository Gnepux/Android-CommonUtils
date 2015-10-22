package com.gnepux.common.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java数据类型相关的辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class JavaUtils {

    /**
     * 将String装换成ArrayList
     *
     * @param string
     * @param split
     * @return
     */
    public static ArrayList<String> stringToArrayList(String string, char split) {
        ArrayList<String> strValueList = new ArrayList<String>(
        Arrays.asList(string.split(String.valueOf(split))));
        return strValueList;
    }

    /**
     * 将ArrayList转成String
     *
     * @param list
     * @return
     */
    public static String arrayListToString(ArrayList<String> list, char split) {
        String strValue = null;
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s + String.valueOf(split));
            strValue = sb.toString();
        }
        if (strValue.length() > 0  && strValue.charAt(strValue.length() - 1) == split) {
            strValue = strValue.substring(0, strValue.length() - 1);
        }
        return strValue;
    }

    /**
     * byte[]转string
     *
     * @param bytes
     * @return
     */
    public static String byteArrayToString(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * string转byte[]
     *
     * @param str
     * @return
     */
    public static byte[] stringToByteArray(String str) {
        return str.getBytes();
    }
}
