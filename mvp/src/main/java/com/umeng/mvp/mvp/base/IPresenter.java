package com.umeng.mvp.mvp.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}