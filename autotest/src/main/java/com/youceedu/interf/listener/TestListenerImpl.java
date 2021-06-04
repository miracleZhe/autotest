package com.youceedu.interf.listener;

import org.apache.log4j.Logger;
import org.testng.*;

import java.util.*;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName TestListenerImpl
 * @Description Todo
 * @Date 2021/4/26 21:32
 */
public class TestListenerImpl implements ITestListener {
    private static Logger logger=Logger.getLogger(TestListenerImpl.class);
    private static List<ITestResult> testRepeatToBeRemoved=new ArrayList<ITestResult>();
    @Override
    public void onFinish(ITestContext context) {

        IResultMap allPassedTests=context.getPassedTests();
        Set<ITestResult> allPassedResult=allPassedTests.getAllResults();
        //第一步 得到失败用例集合
        IResultMap allFailedTests=context.getFailedTests();
        //第二步 Set集合存放Id
        Set<ITestResult> allFailedResult=allFailedTests.getAllResults();
        Set<Integer> failedTestIds=new HashSet<Integer>();
        //第三步 将重复的TestResult存入List集合
        for(ITestResult failedTestResult:allFailedResult){
            //System.out.println("失败用例结果: "+failedTestResult.toString());
            int failedHashCodeId=failedTestResult.toString().hashCode();
            if(failedTestIds.contains(failedHashCodeId)){
                testRepeatToBeRemoved.add(failedTestResult);

            }else {
                failedTestIds.add(failedHashCodeId);
            }
        }
        //第四步 删除重复的失败结果
        for (ITestResult repeatResult:testRepeatToBeRemoved){
            allFailedResult.remove(repeatResult);
        }
        logger.info("失败用例去重完成");

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {


    }


}