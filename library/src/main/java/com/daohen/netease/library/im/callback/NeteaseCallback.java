package com.daohen.netease.library.im.callback;

/**
 * Created by alun on 17/7/19.
 */

public interface NeteaseCallback<T> {

    void onSuccess(T t);

    void onFail(Throwable t);

}
