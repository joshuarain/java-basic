package com.zhl.basic.mybatis.sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        Blog blog = sqlSession.selectOne("selectBlog", 1);
        System.out.println(blog.getId() + "->" + blog.getTitle() + "->" + blog.getContent());

        Map<String,Object> blog1 = sqlSession.selectOne("selectBlog1", 1);
        System.out.println(blog1.get("id") + "->" + blog1.get("title") + "->" + blog1.get("content"));

//        Map<String,Object> pmap = new HashMap<>();
//        pmap.put("id",1);
//        Blog blog2 = sqlSession.selectOne("selectBlog2",pmap);
//        System.out.println(blog2.getTitle());
        List<Blog> blogs = new ArrayList<>();
        Blog blog3=new Blog();
        Blog blog4=new Blog();
        blog3.setTitle("标题3");
        blog3.setContent("内容3");
        blog4.setTitle("标题4");
        blog4.setContent("内容4");
        blogs.add(blog3);
        blogs.add(blog4);
        int result = sqlSession.insert("insertBlogs",blogs);
        System.out.println(blogs.get(0).getId());

//        System.out.println(ids.size());

        sqlSession.commit();//提交事务，方可命中

    }
}
