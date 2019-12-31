package com.zhl.basic.mybatis.api;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lenovo
 * @title: TypeHandlerExt
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 16:16
 */
@MappedTypes({String.class,Integer.class}) //配置注解同时存在，注解被忽略
@MappedJdbcTypes(value={JdbcType.VARCHAR,JdbcType.BLOB}) //配置注解同时存在，注解被忽略
public class TypeHandlerExt extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
