package com.zhl.basic.mybatis.sql;

import java.io.Serializable;

/**
 * @author Lenovo
 * @title: Blog
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 10:22
 */
public class Blog implements Serializable {
    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
