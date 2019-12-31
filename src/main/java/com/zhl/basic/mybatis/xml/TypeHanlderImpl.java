package com.zhl.basic.mybatis.xml;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lenovo
 * @title: TypeHanlderImpl
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 15:47
 */
public class TypeHanlderImpl implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
