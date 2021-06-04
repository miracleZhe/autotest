package com.youceedu.interf.util;



import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;
import com.alibaba.fastjson.JSONPath;

/*
*@author wangzhe
*@Description Pattern工具类
*@Date 21:46 2021/4/30
*@Param
*@Return
**/
public class PatternUtil {
    /*
     * 初始化
     */
    private static String compareResultRegex = "(\\$\\.[\\w]+)=([\\u4e00-\\u9fa5\\w]+)";
    private static String depKeyRegex = "([\\w/]+):([\\$\\.\\w]+)";
    private static String reqDataRegex = "([\\w/]+):([\\$\\.\\w]+)";
    private static Map<String,String> map = new HashMap<String,String>();
    private static Logger logger=Logger.getLogger(PatternUtil.class);
    private static String functionRegex="\\$\\{\\_\\_(\\w+)(\\([\\w,]+\\))\\}";





    /*
    *@author wangzhe
    *@Description 替换请求数据
    *@Date 22:14 2021/4/30
    *@Param [reqData]
    *@Return java.lang.String
    **/
    public static String handlerReqDataOfDep(String reqData){
        Pattern p=Pattern.compile(reqDataRegex);
        Matcher m=p.matcher(reqData);
        while (m.find()){
           reqData=StringUtil.replaceStr(reqData,m.group(),map.get(m.group()));
        }
        logger.info("得到经过处理后的请求数据reqData= "+reqData);
        return reqData;
    }

    /*
    *@author wangzhe
    *@Description 存储depKey，value值
    *@Date 21:49 2021/4/30
    *@Param [depKey, actResult]
    *@Return void
    **/
    public static void storeDepKeyValue(String depKey,String actResult) throws Exception {
        Matcher matcher=getMatcher(depKeyRegex,depKey);
        while (matcher.find()){
            String value=JSONPath.read(actResult, matcher.group(2)).toString();
            map.put(matcher.group(),value);
            logger.info("存储depKey: "+depKey+" 以及对应的value: "+value);
        }
    }

    /*
    *@author wangzhe
    *@Description 获得matcher对象
    *@Date 21:46 2021/4/30
    *@Param [regex, data]
    *@Return java.util.regex.Matcher
    **/
    public static Matcher getMatcher(String regex,String data) throws Exception{
        //建立正则表达式,并执行正则表达式,查看匹配结果
        Pattern pattern = Pattern.compile(regex);
        logger.info("得到Matcher对象");
        return pattern.matcher(data);
    }


    /*
    *@author wangzhe
    *@Description 对比实际结果与预期结果
    *@Date 21:47 2021/4/30
    *@Param [expResult, actResult]
    *@Return void
    **/
    public static void compareResult(String expResult,String actResult) throws Exception {
        Matcher matcher = getMatcher(compareResultRegex,expResult);

        while(matcher.find()){
            String expJsonPath = matcher.group(1);
            String expValve = matcher.group(2);
            String actValue = JSONPath.read(actResult, expJsonPath).toString();
            logger.info("对比预期值： "+expValve+" 与实际值: "+actValue);
            Assert.assertEquals(actValue, expValve);//第1个条件失败,后续条件都不执行
        }

    }
    /* 
    *@author wangzhe
    *@Description 得到结果对比值
    *@Date 21:44 2021/5/24
    *@Param 
    *@Return 
    **/

    public static int compareResultOfDb(String expResult,String actResult) throws Exception {
        Matcher matcher = getMatcher(compareResultRegex,expResult);
        int flag=0;
        List<Integer> list=new ArrayList<Integer>();
        while(matcher.find()){
            String expJsonPath = matcher.group(1);
            String expValve = matcher.group(2);
            String actValue = JSONPath.read(actResult, expJsonPath).toString();
            int status=actValue.equals(expValve)?1:0;
            list.add(status);
        }

        if (!list.contains(0)){
            flag=1;
        }

        return flag;

    }




    /* 
    *@author wangzhe
    *@Description reqData函数表达式处理
    *@Date 22:23 2021/5/10
    *@Param [reqData]
    *@Return void
    **/

    public static String handlerReqDataOfFunction(String reqData) throws Exception {
        Matcher matcher=getMatcher(functionRegex,reqData);
        while (matcher.find()){
            String group=matcher.group();
            String funName=matcher.group(1);
            String[] funParam=matcher.group(2).replace("(","").replace(")","").split(",");

            String value = null;
            if(FuncMappingClassUtil.isContainsInFunc(funName)){
               value= FuncMappingClassUtil.getValue(funName,funParam);
            }
            reqData=StringUtil.replaceStr(reqData,group,value);
        }

        return reqData;
    }


}
