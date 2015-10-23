package com.gnepux.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 数字相关的辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class CUNumUtils {

    /**
     * 获得a-z中的随机字符
     *
     * @return
     */
    public static char getRandomCharacter() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');

        return c;
    }

    /**
     * 获取0到指定范围内的随机数
     *
     * @param maxNum max number till you want get random.
     * @return random number
     */
    public static int getRandom(int maxNum) {
        Random rand = new Random();
        return rand.nextInt(maxNum);
    }

    /**
     * 为数字添加后缀 like: 1st, 3rd.
     *
     * @param number which you have to add postfix
     * @return number in string with postfix
     */
    public static String getPostFixForNumber(int number) {
        String strValue = "";
        // int npos = Integer.valueOf(Pos);

        switch (number % 10) {
            case 1:
                strValue = (number % 100 == 11) ? "th" : "st";
                break;
            case 2:
                strValue = (number % 100 == 12) ? "th" : "nd";
                break;
            case 3:
                strValue = (number % 100 == 13) ? "th" : "rd";
                break;
            default:
                strValue = "th";
                break;
        }
        return number + strValue;
    }

    /**
     * 随机产生32位数字
     *
     * @return UUID字符串
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
