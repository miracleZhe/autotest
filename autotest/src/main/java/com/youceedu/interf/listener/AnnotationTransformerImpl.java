package com.youceedu.interf.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName AnnotationTransformerImpl
 * @Description Todo
 * @Date 2021/4/21 22:40
 */
public class AnnotationTransformerImpl implements IAnnotationTransformer {


    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
    IRetryAnalyzer iRetryAnalyzer=annotation.getRetryAnalyzer();
    if(iRetryAnalyzer==null) {
        annotation.setRetryAnalyzer(IRetryAnalyzerImpl.class);
        }

    //test
    }
}