package com.youceedu.interf.util;

import com.youceedu.interf.function.Function;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName FunMapUtil
 * @Description Todo
 * @Date 2021/5/21 21:41
 */
public class FunMapUtil {
    /*
    开发funName与类的映射关系：
    1.得到funcMap 的value（类）
    2.得到funcMap的key（类名）
    3.建立映射关系
    4.封装方法
     */
    public static Map<String,Class<? extends Function>> funcMap=new HashMap<String,Class<? extends Function>>();

    static {
        try {
            //1.得到funcMap的value值--->类
            Class<?> function=Class.forName("com.youceedu.interf.function.Function");
            String pk=function.getPackage().getName();
            String path =pk.replace(".","/");
            String classPath=function.getClassLoader().getResource(path).getPath();

            File file=new File(classPath);
            for (File f:file.listFiles()){
                String fileName =f.getName();
                if (fileName.endsWith(".class")){
                    Class<?> c =Class.forName(pk+"."+fileName.substring(0,fileName.length()-6));

                    //2.得到funcMap的key（函数名）
                    if (!function.equals(c)&&function.isAssignableFrom(c)){
                        Function funcObject=(Function) c.newInstance();
                        String funcName=funcObject.getFunction();
                        funcMap.put(funcName,funcObject.getClass());
                    }

                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public static boolean isFunc(String funcName){
        return funcMap.containsKey(funcName);
    }



    public static String getValue(String funcName,String[] args) throws InstantiationException, IllegalAccessException {
        String result=funcMap.get(funcName).newInstance().execParam(args);
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {


    }
}