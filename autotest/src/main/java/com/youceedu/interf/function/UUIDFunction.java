package com.youceedu.interf.function;

import java.util.UUID;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName UUIDFunction
 * @Description Todo
 * @Date 2021/5/10 22:27
 */
public class UUIDFunction implements Function{
    @Override
    public String getFunction() {
        return "UUID";
    }

    @Override
    public String execParam(String[] args) {
        String result= null;
        if (args.length==0){
            result= UUID.randomUUID().toString().replace("-","");
        }
        return result;
    }
}