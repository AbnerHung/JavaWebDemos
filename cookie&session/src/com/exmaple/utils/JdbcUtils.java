package com.exmaple.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类使用Durid连接池
 */

public class JdbcUtils {

    private static DataSource ds;

    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            InputStream stream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(stream);

            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取连接池对象
     * @return DataSource
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接Connection对象
     * @return ds.getConnection()
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}
