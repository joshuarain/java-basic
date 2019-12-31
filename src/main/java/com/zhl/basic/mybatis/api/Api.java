package com.zhl.basic.mybatis.api;

import org.apache.ibatis.jdbc.AbstractSQL;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

import static org.apache.ibatis.jdbc.SelectBuilder.FROM;
import static org.apache.ibatis.jdbc.SelectBuilder.SELECT;

/**
 * @author Lenovo
 * @title: Api
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 9:28
 */
public class Api {
    public static void main(String[] args) {
        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Blog blog = sqlSession.selectOne("selectBlog", 1);
        System.out.println(blog.getId() + "->" + blog.getTitle() + "->" + blog.getContent());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog b1 = blogMapper.selectBlog(1);
        System.out.println(b1.getId() + "->" + b1.getTitle() + "->" + b1.getContent());

    }

    // Anonymous inner class
    public String deletePersonSql1() {
//        return new SQL() {{
//            DELETE_FROM("PERSON");
//            WHERE("ID = #{id}");
//        }}.toString();
    return new SQL(){
        {
            DELETE_FROM("PERSON");
            WHERE("ID=#{id}");
        }
    }.toString();

    }

    // Builder / Fluent style
    public String insertPersonSql2() {
        String sql = new SQL()
                .INSERT_INTO("PERSON")
                .VALUES("ID, FIRST_NAME", "#{id}, #{firstName}")
                .VALUES("LAST_NAME", "#{lastName}")
                .toString();
        return sql;
    }

    public String fluentStyle(){
        return new SQL().SELECT("P.ID,P.USERNAME").FROM("PERSON P").WHERE("P.ID=#{id}").toString();
    }

    // With conditionals (note the final parameters, required for the anonymous inner class to access them)
    public String selectPersonLike(final String id, final String firstName, final String lastName) {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
            FROM("PERSON P");
            if (id != null) {
                WHERE("P.ID like #{id}");
            }
            if (firstName != null) {
                WHERE("P.FIRST_NAME like #{firstName}");
            }
            if (lastName != null) {
                WHERE("P.LAST_NAME like #{lastName}");
            }
            ORDER_BY("P.LAST_NAME");
        }}.toString();
    }

    public String deletePersonSql() {
        return new SQL() {{
            DELETE_FROM("PERSON");
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String insertPersonSql3() {
        return new SQL() {{
            INSERT_INTO("PERSON");
            VALUES("ID, FIRST_NAME", "#{id}, #{firstName}");
            VALUES("LAST_NAME", "#{lastName}");
        }}.toString();
    }

    public String updatePersonSql() {
        return new SQL() {{
            UPDATE("PERSON");
            SET("FIRST_NAME = #{firstName}");
            WHERE("ID = #{id}");
        }}.toString();
    }
}
