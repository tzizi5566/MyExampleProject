package com.example.kop.myexampleproject.net;

import android.content.Context;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:43
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private Context mContext;

    protected BaseObserver() {

    }

    protected BaseObserver(Context context) {
        mContext = context;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        ApiException apiException = ApiErrorHelper.handleCommonError(e);
        onFail(apiException);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(ApiException e);

    public abstract void noLogin(ApiException e);
}
