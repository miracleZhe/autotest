package com.youceedu.interf.util;

import com.youceedu.interf.model.AutoLog;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilTest {
    private String filepath=null;
    private static Logger logger=Logger.getLogger(ExcelUtilTest.class);
    private static List<AutoLog> list=new ArrayList<AutoLog>();


    @Parameters({"filepath"})
    @BeforeTest
    public void beforeTest(String filepath) {//D:\\testcase.xlsx"
        this.filepath=filepath;
        logger.info("传入excel路径 filepath: "+filepath);
    }
    @Test(dataProvider = "testdata")
    public void testHttpReq(String id, String isExec,String testCase,String reqType,String reqHost,String reqInterface,String reqData,String expResult,String isDep,String depKey) throws Exception {
        String actResult=null;
        String url=reqHost+reqInterface;
        //id=123456&passwd=123456&t=${__Time(YMDHMS,)}
        //reqdata函数表达式处理


        reqData=PatternUtil.handlerReqDataOfFunction(reqData);
        Reporter.log("函数处理后的data"+reqData);
        reqData=PatternUtil.handlerReqDataOfDep(reqData);
        Reporter.log("测试用例编号"+id);
        Reporter.log("测试用例"+testCase);
        Reporter.log("用例接口: "+url);
        Reporter.log("替换后的data: "+reqData);
        if("YES".equals(isExec)){
            if("GET".equals(reqType)){
                actResult=HttpSendReq.sendGet(url,reqData);
            }else{
                actResult=HttpSendReq.sendPost(url,reqData);

            }
        }else{
            Reporter.log("由于excel中test_is_exec为no，不执行该case");
        }
        //判断接口是否被依赖
        if("YES".equals(isDep)){
          PatternUtil.storeDepKeyValue(depKey,actResult);
        }
        //收集数据存储到DB
       list.add(new AutoLog(testCase,reqType,url,reqData,expResult,actResult,PatternUtil.compareResultOfDb(expResult,actResult),DateUtil.getDateTime()));

        Reporter.log("预期结果："+expResult);
        Reporter.log("实际结果："+actResult);
        PatternUtil.compareResult(expResult,actResult);



    }




    @DataProvider(name="testdata")
    public Object[][] dp() throws Exception {
        ExcelUtil excel=new ExcelUtil(this.filepath);
        logger.info("给测试方法提供测试数据");
        return excel.getArrayCellValue(0);
    }

    @AfterTest
    public void afterTest() throws SQLException {
        DbcpUtil.paramSqlUpdate(list);
    }
}