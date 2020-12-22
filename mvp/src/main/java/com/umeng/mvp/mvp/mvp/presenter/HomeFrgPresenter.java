package com.umeng.mvp.mvp.mvp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.umeng.mvp.mvp.base.BasePresenter;
import com.umeng.mvp.mvp.callback.RxObserverCallBack;
import com.umeng.mvp.mvp.mvp.model.RxOpretorImpl;
import com.umeng.mvp.mvp.mvp.model.constants.ApiConstants;
import com.umeng.mvp.mvp.mvp.ui.fragment.HomeFragment;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HomeFrgPresenter extends BasePresenter {
    private RxOpretorImpl mImpl;
    private HomeFragment mHomeFragment;

    public HomeFrgPresenter(HomeFragment fragment) {
        mImpl = new RxOpretorImpl();
        this.mHomeFragment = fragment;
    }

    @Override
    public void start() {
        //2.向M层进行数据请求，请求到数据之后将数据返回给V层
        mImpl.rxGetRequest(ApiConstants.BAIDU_LIST, new RxObserverCallBack<Object>() {
            @Override
            public void onSuccessData(Object obj) {
                if (mHomeFragment != null) {
                    ResponseBody responseBody = (ResponseBody) obj;
                    try {
                        String string = responseBody.string();
                        //TODO gson解析
                        mHomeFragment.onScuccess(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mHomeFragment.onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onErrorMsg(String msg) {
                if (mHomeFragment != null) {
                    mHomeFragment.onError(msg);
                }
            }
        });
    }
}