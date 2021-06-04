package com.youceedu.interf.util;

import java.util.Random;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName RandomUtil
 * @Description 随机工具类
 * @Date 2021/5/15 21:06
 */
public class RandomUtil {
    public  static Random random=new Random();
    public  static String string="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /*
    *@author wangzhe
    *@Description 范围内的随机整数
    *@Date 21:13 2021/5/15
    *@Param [min, max]
    *@Return int
    **/
    public static int getRandomInt(int min,int max){
        return random.nextInt(max)+min;
    }
    /*
    *@author wangzhe
    *@Description 得到long范围的随机数
    *@Date 21:15 2021/5/15
    *@Param []
    *@Return long
    **/
    public static long getRandomLong(){
        return random.nextLong();
    }
    /*
    *@author wangzhe
    *@Description 得到随机布尔
    *@Date 21:16 2021/5/15
    *@Param []
    *@Return boolean
    **/
    public static boolean getBoolean(){
        return random.nextBoolean();
    }
    /*
    *@author wangzhe
    *@Description 得到随机浮点数
    *@Date 21:18 2021/5/15
    *@Param [min, max]
    *@Return float
    **/
    public static float getFloat(float min,float max){
        return random.nextFloat()*max+min;
    }
    /* 
    *@author wangzhe
    *@Description 得到double类型的数
    *@Date 21:20 2021/5/15
    *@Param [min, max]
    *@Return double
    **/
    public static double getDouble(double min,double max){
        return random.nextDouble()*max+min;
    }

    public static String getString(int length){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i <length ; i++) {
            int number=random.nextInt(string.length());
            stringBuffer.append(string.charAt(number));
        }
        return stringBuffer.toString();
    }



    public static void main(String[] args) {

        System.out.println(getRandomLong());
    }
}