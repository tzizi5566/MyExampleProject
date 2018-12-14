package com.example.kop.myexampleproject.base;

import androidx.lifecycle.LifecycleOwner;

/**
 * MVP模式中V的基类接口
 * Created by KOP on 2016/5/8.
 */
public interface BaseView<T> {

    /**
     * 使用fragment作为view时，将activity中的presenter传递给fragment
     */
    void setPresenter(T presenter);

    void showLoading();

    void stopLoading();

    LifecycleOwner getLifecycleOwner();
}

