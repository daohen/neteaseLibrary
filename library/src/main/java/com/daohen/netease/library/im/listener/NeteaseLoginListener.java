package com.daohen.netease.library.im.listener;

import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * Created by alun on 17/7/19.
 */

public interface NeteaseLoginListener {

    void onSuccess(LoginInfo info);
    void onFail(Throwable t);
}
