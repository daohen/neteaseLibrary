package com.daohen.netease.library.im.callback;

import com.netease.nimlib.sdk.Observer;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/24 11:28
 */
public abstract class AbstractObserver<T> implements Observer<T> {

    public static <B> AbstractObserver getDefault(Observer<B> observer){
        return new AbstractObserver<B>(observer) {
            @Override
            public void onEventBefore(B b) {
            }
        };
    }

    private Observer<T> observer;

    public AbstractObserver(Observer<T> observer){
        this.observer = observer;
    }

    public abstract void onEventBefore(T t);

    @Override
    public void onEvent(T t) {
        onEventBefore(t);
        if (observer != null)
            observer.onEvent(t);
    }
}
