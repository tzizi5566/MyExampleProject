package com.example.kop.myexampleproject.ui.sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * 功    能: Room内对应的表对象
 * 创 建 人: KOP
 * 创建日期: 2018/12/17 13:20
 */
@Entity(indices = {@Index(value = {"NAME"}, unique = true)})
public class User {

    @ColumnInfo(name = "AGE")
    private int age;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "NAME")
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
