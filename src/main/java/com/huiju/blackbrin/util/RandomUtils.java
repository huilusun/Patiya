package com.huiju.blackbrin.util;


import java.util.Random;

/**
 * 类说明：获取随机数
 */
public class RandomUtils {

    /**
     * 获取包含一位小数点的随机数
     */
    public static Double getRandom4Rating() {
    	int a = new Random().nextInt(5);
    	int b = new Random().nextInt(10);
    	double d = Double.parseDouble(a + "." + b);
        return d;
    }
}
