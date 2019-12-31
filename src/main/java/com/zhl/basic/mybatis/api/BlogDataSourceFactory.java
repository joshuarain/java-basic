package com.zhl.basic.mybatis.api;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author Lenovo
 * @title: BlogDataSourceFactory
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 9:32
 */
public class BlogDataSourceFactory {

    public static DataSource getBlogDataSource(){
        DataSource dataSource = new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true","root","root");
                return conn;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {

            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {

            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        };

        return dataSource;
    }
}
