package com.example.kop.myexampleproject.entity;

/**
 * 功    能: 首页对象
 * 创 建 人: KOP
 * 创建日期: 2019-05-24 17:03
 */
public class MainBean {

    private String title;

    private String content;

    private Class clazz;

    public MainBean(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public MainBean(final String title, final String content, final Class clazz) {
        this.title = title;
        this.content = content;
        this.clazz = clazz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(final Class clazz) {
        this.clazz = clazz;
    }
}
