package com.youceedu.interf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName JsonUtil
 * @Description Todo
 * @Date 2021/4/12 21:31
 */
public class JsonUtil {
    /**
     * @Title: isJsonString
     * @Description: 判断jsonStr是否为json字符串
     * @param: @param jsonStr
     * @param: @return
     * @return: boolean
     * @throws
     */
    public static boolean isJsonString(String jsonStr) {
        //初始化
        boolean flag = false;
        try {
            JSONObject json = JSON.parseObject(jsonStr);
            flag = true;
        }catch(Exception e) {
        }
        return flag;
    }

    /**
     * @Title: isJsonArray
     * @Description: 判断jsonArray是否为json数组
     * @param: @param jsonArray
     * @param: @return
     * @return: boolean
     * @throws
     */
    public static boolean isJsonArray(String jsonArray) {
        //初始化
        boolean flag = false;
        try {
            JSONArray jSONArray = JSONArray.parseArray(jsonArray);
            flag = true;
        }catch(Exception e) {
        }
        return flag;
    }

    public static void main(String[] args) {
        //流水线代码
        //String jsonStr = "{\"status\":\"true\",\"msg\":\"登录成功!\"}";
        //System.out.println("jsonStr字符串是否为json格式 :" + isJsonString(jsonStr));

        String jsonArray = "[{\"status\":\"true\",\"msg\":\"登录成功!\"},{\"name\":\"zhangsan\",\"score\":90}]";
        System.out.println("jsonArray字符串是否为json数组格式 :" + isJsonArray(jsonArray));
    }
}