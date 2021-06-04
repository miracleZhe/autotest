package com.youceedu.interf.util;
//package com.youceedu.interf.function;
import com.youceedu.interf.function.Function;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName FuncMappingClassUtil
 * @Description Todo
 * @Date 2021/5/15 22:07
 */
public class FuncMappingClassUtil {
    /**
     * 初始化
     * funcMap数据格式:
     * {"Random"=RandomFunction.class}
     * 第一步 得到map的Value
     * 第二步 得到map的Key
     */
    public static Map<String,Class<? extends Function>> funcMap=new HashMap<String,Class<? extends Function>>();

    /**
     * 建立funcName与Function子类的映射关系
     */
    static {
        try {
            //得到funcMap的Value  Function的子类
            Class<?> function=Class.forName("com.youceedu.interf.function.Function");
            //得到包名
            String pk=function.getPackage().getName();
            String path=pk.replace(".","/");
            String classPath=function.getClassLoader().getResource(path).getPath();//java->class文件->类加载器->jvm


            File file=new File(classPath);
            for(File f:file.listFiles()){
                String fileName=f.getName();
                if(fileName.endsWith(".class")){
                    Class<?> c=Class.forName(pk+"."+fileName.substring(0,fileName.length()-6));
                    //c类是Function类的子类
                    if (!function.equals(c)&&function.isAssignableFrom(c)){
                        //2.得到funcMap的Key
                        Function funcObject= (Function)c.newInstance();
                        String funcName=funcObject.getFunction();
                        funcMap.put(funcName,funcObject.getClass());
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    *@author wangzhe
    *@Description 判断funcMap是否包含对应的函数名
    *@Date 22:58 2021/5/15
    *@Param [funcName]
    *@Return boolean
    **/
    public static boolean isContainsInFunc(String funcName){
       return funcMap.containsKey(funcName);
    }

    /*
    *@author wangzhe
    *@Description 根据函数名得到autotest工程对应的类，并基于类对象调用execParam方法执行用户的参数
    *@Date 23:06 2021/5/15
    *@Param [funcName, args]
    *@Return java.lang.String
    **/
    public static String getValue(String funcName,String args[]) throws InstantiationException, IllegalAccessException {
        return  funcMap.get(funcName).newInstance().execParam(args);
    }
    public static void main(String[] args) {

    }
}