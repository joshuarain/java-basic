package com.zhl.basic.mybatis.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lenovo
 * @title: BlogMapper
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 9:35
 */
@Mapper
public interface BlogMapper {
    @Select("select * from Blog where id = #{id}")
    Blog selectBlog(int id);
}
