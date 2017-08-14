package com.daohen.netease.library.callback;

import com.netease.nimlib.sdk.RequestCallback;


public abstract class AbstractRequestCallback<T> implements RequestCallback<T> {

    public static <B> AbstractRequestCallback<B> getDefault(NeteaseCallback<B> callback){
        return new AbstractRequestCallback<B>(callback) {
            @Override
            public void onSuccessBefore(B param) {

            }
        };
    }

    private NeteaseCallback<T> callback;

    public AbstractRequestCallback(NeteaseCallback<T> callback){
        this.callback = callback;
    }

    public abstract void onSuccessBefore(T param);

    @Override
    public void onSuccess(T param) {
        onSuccessBefore(param);
        if (callback != null)
            callback.onSuccess(param);
    }

    @Override
    public void onFailed(int code) {
        if (callback != null)
            callback.onFail(new Throwable("fail code:"+code));
    }

    @Override
    public void onException(Throwable exception) {
        if (callback != null)
            callback.onFail(exception);
    }
}
