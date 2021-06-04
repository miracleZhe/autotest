package com.youceedu.interf.util;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName ExcelUtil
 * @Description Todo
 * @Date 2021/4/13 23:13
 */
public class ExcelUtil {
    private String filepath;
    private static Logger logger=Logger.getLogger(ExcelUtil.class);
    /*
    *@author wangzhe
    *@Description 构造方法
    *@Date 23:14 2021/4/13
    *@Param [filename]
    *@Return
    **/
    public ExcelUtil(String filename){
        this.filepath=filename;
        logger.info("实例化类，入参filename= "+filename);

    }
    /*
    *@author wangzhe
    *@Description 获取Wb对象
    *@Date 23:15 2021/4/13
    *@Param []
    *@Return org.apache.poi.xssf.usermodel.XSSFWorkbook
    **/
    public XSSFWorkbook getWb() throws Exception{
        XSSFWorkbook wb =new XSSFWorkbook(this.filepath);
        logger.info("成功得到XSSFWorkbook对象 wb");
        return wb;
    }
    /*
    *@author wangzhe
    *@Description 获取sheet对象
    *@Date 23:15 2021/4/13
    *@Param [index]
    *@Return org.apache.poi.xssf.usermodel.XSSFSheet
    **/
    public XSSFSheet getSheet(int index) throws Exception{
        XSSFSheet sheet=getWb().getSheetAt(index);
        logger.info("成功得到sheet对象");
        return sheet;
    }
    /*
    *@author wangzhe
    *@Description 获取值
    *@Date 23:16 2021/4/13
    *@Param [index, rowIndex, cellIndex]
    *@Return java.lang.Object
    **/
    public Object getValue(int index,int rowIndex,int cellIndex) throws Exception{
        XSSFRow row=getSheet(index).getRow(rowIndex);
        XSSFCell cell=row.getCell(cellIndex);
        Object value=getValueFromCellTyoe(cell);
        logger.info("入参index= "+index+" rowIndex= "+rowIndex+" cellIndex= "+cellIndex+"单元格值= "+value);
        return value;

    }
    /*
    *@author wangzhe
    *@Description 根据类型获取值
    *@Date 23:16 2021/4/13
    *@Param [cell]
    *@Return java.lang.Object
    **/
    public Object getValueFromCellTyoe(XSSFCell cell){

        CellType celltype=cell.getCellTypeEnum();
        Object value=null;
        switch(celltype){
            case _NONE:
                value=" ";
                break;
            case NUMERIC:
                value=cell.getNumericCellValue();
                break;
            case STRING:
                value=cell.getStringCellValue();
                break;
            case FORMULA:
                value=cell.getCellFormula();
                break;
            case BLANK:
                value=" ";
                break;
            case BOOLEAN:
                value=cell.getBooleanCellValue();
                break;
            case ERROR:
                value=cell.getErrorCellValue();
                break;
            default:
                value=cell.getDateCellValue();
                break;


        }
        logger.info("单元格类型: "+celltype+" 返回值= "+value);
        return value;

    }
    /*
    *@author wangzhe
    *@Description 获取数组
    *@Date 23:17 2021/4/13
    *@Param [index]
    *@Return java.lang.Object[][]
    **/
    public Object[][] getArrayCellValue(int index) throws Exception{
        XSSFSheet sheet=getSheet(index);
        Object[][] testcasedata=new Object[sheet.getLastRowNum()][10];
        for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
            XSSFRow row=sheet.getRow(rowNum);
            for(int cellNum=row.getFirstCellNum();cellNum<row.getLastCellNum();cellNum++){
                XSSFCell cell=row.getCell(cellNum);
                testcasedata[rowNum-1][cellNum]=getValueFromCellTyoe(cell);
            }


        }
        logger.info("excel内测试用例已成功存入到二维数组testcasedata中");
        return testcasedata;
    }






    public static void main(String[] args) throws Exception {
        ExcelUtil excelUtil=new ExcelUtil("D:\\testcase.xlsx");
        Object[][] value1=excelUtil.getArrayCellValue(0);
        System.out.println(value1[0][1]);

    }
}