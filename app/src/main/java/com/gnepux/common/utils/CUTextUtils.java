package com.gnepux.common.utils;

import android.util.Patterns;
import android.widget.EditText;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本辅助类
 * Created by Gnepux on 2015/10/20.
 */
public class CUTextUtils {

    /**
     * check EditText is empty or not
     *
     * @param edText pass EditText for check is empty or not
     * @return true or false
     */
    public static boolean isEmptyEditText(EditText edText) {
        if (edText.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }

    /**
     * 判断Email格式是否正确
     *
     * @param email pass email id in string
     * @return true when its valid otherwise false
     */
    public static boolean isEmailIdValid(String email) {
/*        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;*/

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * 判断网址格式是否正确
     *
     * @param url
     * @return
     */
    public static boolean isWebsiteUrlValid(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

}
