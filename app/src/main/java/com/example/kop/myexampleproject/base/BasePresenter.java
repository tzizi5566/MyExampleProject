package com.example.kop.myexampleproject.base;

/**
 * MVP模式中P的基类接口
 * Created by KOP on 2016/5/8.
 */
public interface BasePresenter {

  /**
   * 页面初始化的时候做的事情，根据业务决定是否需要
   */
  void start();

  void destroy();
}
