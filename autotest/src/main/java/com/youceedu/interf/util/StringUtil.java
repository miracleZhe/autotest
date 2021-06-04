package com.youceedu.interf.util;

/**
 *
 * @Title:  StringUtil.java
 * @Package com.youceedu.interf.util
 * @Description: String工具类
 * @author: wangyanzhao
 * @date:   2021年4月24日 下午3:28:46
 * @version V1.0
 * @Copyright: 2021 www.youceedu.com All rights reserved.
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class StringUtil {

    public static String replaceStr(String sourceStr,String matchStr,String replaceValue){
        //截取左子串
        int replaceIndex=sourceStr.indexOf(matchStr);
        String leftStr=sourceStr.substring(0,replaceIndex);
        //截取右子串
        String rightStr=sourceStr.substring(replaceIndex+matchStr.length());

        return leftStr+replaceValue+rightStr;
    }


    public static void main(String[] args) {
        String sourceStr="oldPasswd=/api/loginCheck:$.stateCode&newPasswd=123456&reNewPasswd=123456";
        String matchStr="/api/loginCheck:$.stateCode";
        String replaceValue ="1";
        System.out.println(replaceStr(sourceStr,matchStr,replaceValue));

    }

}
