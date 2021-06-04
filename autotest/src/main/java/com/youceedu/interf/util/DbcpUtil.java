package com.youceedu.interf.util;

import com.youceedu.interf.model.AutoLog;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.List;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName DbcpUtil
 * @Description java基于连接池操作数据库
 * @Date 2021/5/23 23:34
 */
public class DbcpUtil {
    private static BasicDataSource pool=null;
    private static String sql="insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) values(?,?,?,?,?,?,?,?)";

    static {
        if (pool==null) {
            pool = new BasicDataSource();
            //A.连接池连接DB的基本信息
            pool.setDriverClassName("com.mysql.jdbc.Driver");
            pool.setUrl("jdbc:mysql://8.131.245.231:3306/interface?characterEncoding=utf-8");
            pool.setUsername("root");
            pool.setPassword("123456");
            //B.连接池配置
            pool.setInitialSize(10);
            pool.setMinIdle(10);
            pool.setMaxIdle(10);
            pool.setMaxActive(10);
            //C.借连接归还连接相关配置
            pool.setMaxWait(2000);
            pool.setTestOnBorrow(false);
            pool.setTestOnReturn(false);
            //E.其他方法
            pool.setPoolPreparedStatements(true);
        }
    }
    /*
    *@author wangzhe
    *@Description 得到连接
    *@Date 23:47 2021/5/23
    *@Param []
    *@Return java.sql.Connection
    **/
    public synchronized static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
    /*
    *@author wangzhe
    *@Description 特定场景操作数据库
    *@Date 23:47 2021/5/23
    *@Param [list, sql]
    *@Return int[]
    **/
    public static int[] paramSqlUpdate(List<AutoLog> list) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        for (AutoLog tmp:list){
            ps.setString(1,tmp.getTestCase());
            ps.setString(2,tmp.getReqType());
            ps.setString(3,tmp.getReqUrl());
            ps.setString(4,tmp.getReqData());
            ps.setString(5,tmp.getExpResult());
            ps.setString(6,tmp.getActResult());
            ps.setInt(7,tmp.getResult());
            ps.setString(8,tmp.getExecTime());
            ps.addBatch();
        }
        int[] count=ps.executeBatch();
        close(connection,ps,null);
        return count;
    }

    /*
    *@author wangzhe
    *@Description 归还连接
    *@Date 23:50 2021/5/23
    *@Param [connection, preparedStatement, rs]
    *@Return void
    **/
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet rs) throws SQLException {
        if (rs!=null){
            rs.close();
        }
        if (preparedStatement!=null){
            preparedStatement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }
    public static void main(String[] args) throws SQLException {

    }
}