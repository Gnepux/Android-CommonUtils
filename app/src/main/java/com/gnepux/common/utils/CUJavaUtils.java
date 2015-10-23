package com.gnepux.common.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Java数据类型相关的辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class CUJavaUtils {

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

    /**
     * 对象装byte[]
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte[]转对象
     *
     * @param bytes
     * @return
     */
    public static Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * DoubleStr转int
     */
    public static String Double2Int(String doubleNum) {
        return ((int)Double.parseDouble(doubleNum)) + "";
    }

    /**
     * IntStr转int
     *
     * @param intNum
     * @return
     */
    public static int String2Int(String intNum) {
        if (TextUtils.isEmpty(intNum)) {
            return 0;
        }
        return Integer.parseInt(intNum);
    }

    /**
     * LongStr转Long
     *
     * @param longNum
     * @return
     */
    public static long String2Long(String longNum) {
        if (TextUtils.isEmpty(longNum)) {
            return 0;
        }
        return Long.parseLong(longNum);
    }
}
