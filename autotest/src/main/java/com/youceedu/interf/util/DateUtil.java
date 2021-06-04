package com.youceedu.interf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName DateUtil
 * @Description 日期时间工具类
 * @Date 2021/5/15 21:31
 */
public class DateUtil {
    public static Date date=new Date();
    /* 
    *@author wangzhe
    *@Description 得到时间戳
    *@Date 21:37 2021/5/15
    *@Param []
    *@Return long
    **/
    public static long getTimeImpl(){
        return System.currentTimeMillis();
    }
    /*
    *@author wangzhe
    *@Description 获取当前系统时间
    *@Date 21:41 2021/5/15
    *@Param []
    *@Return java.lang.String
    **/
    public static String getTime(){
        return  DateFormat.getTimeInstance().format(new Date());
    }
    /*
    *@author wangzhe
    *@Description 获取系统日期
    *@Date 21:44 2021/5/15
    *@Param []
    *@Return java.lang.String
    **/
    public static String getDate(){

        return DateFormat.getDateInstance().format(new Date());
    }
    /*
    *@author wangzhe
    *@Description 获取日期时间
    *@Date 21:45 2021/5/15
    *@Param []
    *@Return java.lang.String
    **/
    public static String getDateTime(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }
    /* 
    *@author wangzhe
    *@Description 达到规定的日期时间格式
    *@Date 21:48 2021/5/15
    *@Param [pattern]
    *@Return java.lang.String
    **/
    public static String getPatternDateTime(String pattern){
        
        return new SimpleDateFormat(pattern).format(new Date());
    }
    public static void main(String[] args) {
        System.out.println(getPatternDateTime("yyyy-MM-dd HH:mm:ss:SSS"));
    }
}