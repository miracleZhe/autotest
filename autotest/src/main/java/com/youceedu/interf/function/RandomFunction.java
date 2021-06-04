package com.youceedu.interf.function;

import com.youceedu.interf.util.RandomUtil;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName RandomFunction
 * @Description Random函数表达式
 * @Date 2021/5/10 22:24
 */
public class RandomFunction  implements Function {
    @Override
    public String getFunction() {
        return "Random" ;
    }

    @Override
    public  String execParam(String[] args) {
        //args[1,1,100]
        String result=null;
        if (args[0].equals("1")){

            int min=Integer.valueOf(args[1]);
            int max=Integer.valueOf(args[2]);
            result=String.valueOf(RandomUtil.getRandomInt(min,max));
        }else if (args[0].equals("2")){
            //随机布尔
            result=String.valueOf(RandomUtil.getBoolean());
        }else if (args[0].equals("3")){
            float min=Float.valueOf(args[1]);
            float max=Float.valueOf(args[2]);
            result=String.valueOf(RandomUtil.getFloat(min,max));
        }else if (args[0].equals("4")){
            double min=Double.valueOf(args[1]);
            double max=Double.valueOf(args[2]);
            result=String.valueOf(RandomUtil.getDouble(min,max));
        }else if (args[0].equals("5")){
            result=RandomUtil.getString(Integer.valueOf(args[1]));
        }

        return result;
    }


}