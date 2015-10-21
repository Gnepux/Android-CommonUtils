package com.gnepux.common.utils;

import java.util.Random;

/**
 * 数字相关的辅助类
 * Created by Gnepux on 2015/10/21.
 */
public class NumUtils {

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

}
