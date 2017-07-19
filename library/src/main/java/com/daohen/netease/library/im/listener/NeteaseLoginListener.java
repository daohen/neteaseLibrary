package com.daohen.netease.library.im.listener;

/**
 * Created by alun on 17/7/19.
 */

public interface NeteaseLoginListener<T> {

    void onSuccess(T t);

    void onFail(Throwable t);

}
