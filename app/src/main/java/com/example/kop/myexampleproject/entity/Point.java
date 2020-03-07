package com.example.kop.myexampleproject.entity;

/**
 * 功    能: 坐标点
 * 创 建 人: KOP
 * 创建日期: 2019-05-27 16:14
 */
public class Point {

    private float x;

    private float y;

    private String index;

    public Point() {
    }

    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Point(final float x, final float y, final String index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public float getX() {
        return x;
    }

    public void setX(final float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(final String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", index='" + index + '\'' +
                '}';
    }
}
