package com.umeng.mvp.mvp.base;

public interface IBaseView<T> {
    void onScuccess(T t);
    void onError(String msg);
}