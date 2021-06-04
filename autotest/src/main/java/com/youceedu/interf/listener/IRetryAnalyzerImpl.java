package com.youceedu.interf.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName IRetryAnalyzerImpl
 * @Description Todo
 * @Date 2021/4/22 22:41
 */
public class IRetryAnalyzerImpl implements IRetryAnalyzer {//有失败的用例才会运行
    private int retryCount=1;
    private int retryMaxCount=3;
    private static Logger logger=Logger.getLogger(IRetryAnalyzerImpl.class);
    @Override
    public boolean retry(ITestResult result) {//true重试
        if(retryCount<=retryMaxCount){
            retryCount++;
            logger.info("失败用例重试第"+retryCount+" 次");
            return true;
        }
        retryCount=1;
        return false;
        //test
    }
}