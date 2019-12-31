package com.zhl.basic.mybatis.xml;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lenovo
 * @title: Xml
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 9:37
 */
public class Xml {
    public static void main(String[] args) throws IOException {
        String resource = "com/zhl/basic/mybatis/sql/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
//        List<Blog> blog = sqlSession.selectList("selectBlog",1);
        Blog blog = sqlSession.selectOne("selectBlog",1);
        sqlSession.flushStatements();
        sqlSession.commit();//提交事务，方可命中
        System.out.println(blog.getTitle());
        System.out.println(blog.getId()+"->"+blog.getTitle()+"->"+blog.getContent());

        SqlSession session = sqlSessionFactory.openSession();
        Blog b1 = session.selectOne("selectBlog",1);
        System.out.println(b1.getId()+"->"+b1.getTitle()+"->"+b1.getContent());
    }
}
