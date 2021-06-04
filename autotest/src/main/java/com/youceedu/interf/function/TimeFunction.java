package com.youceedu.interf.function;

import com.youceedu.interf.util.DateUtil;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName TimeFunction
 * @Description Todo
 * @Date 2021/5/10 22:29
 */
public class TimeFunction implements Function {

    @Override
    public String getFunction() {
        return "Time";
    }

    @Override
    public String execParam(String[] args) {
        String result=null;
        if (args.length==0){
            //时间戳
            result=String.valueOf(DateUtil.getTimeImpl());
        }else if (args.length==1&&args[0].equals("YMDHMS")){
            result=DateUtil.getDateTime();
        }else if (args.length==1&&args[0].equals("YMD")){
            result=DateUtil.getDate();
        }else if (args.length==1&&args[0].equals("HMS")){
            result=DateUtil.getTime();
        }else{
            result=DateUtil.getPatternDateTime(args[0]);
        }
        return result;
    }
}