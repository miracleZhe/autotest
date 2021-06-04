package com.youceedu.interf.function;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName Md5Function
 * @Description Md5函数表达式
 * @Date 2021/5/10 22:28
 */
public class Md5Function implements Function{
    @Override
    public String getFunction() {
        return "Md5";
    }

    @Override
    public String execParam(String[] args) {
        String result=null;
        if (args.length==1){
            result=DigestUtils.md5Hex(args[0]);
        }
        return result;
    }
}