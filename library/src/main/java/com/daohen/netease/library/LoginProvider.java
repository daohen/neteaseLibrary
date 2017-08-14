package com.daohen.netease.library;

import com.daohen.netease.library.callback.NeteaseCallback;
import com.daohen.netease.library.manager.AuthServiceManager;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.OnlineClient;

/**
 * Created by alun on 17/7/18.
 */

public class LoginProvider {

    public static LoginProvider get(){
        return gDefault.get();
    }

    public void login(LoginInfo info, final NeteaseCallback<LoginInfo> callback){
        AuthServiceManager.get().login(info, callback);
    }

    public void logout(){
        AuthServiceManager.get().getAuthService().logout();
    }

    public StatusCode getCurrentStatus(){
        return NIMClient.getStatus();
    }

    public void kickOtherClient(OnlineClient onlineClient, NeteaseCallback<Void> callback){
        AuthServiceManager.get().kickOtherClient(onlineClient, callback);
    }

    private static final Singleton<LoginProvider> gDefault = new Singleton<LoginProvider>() {
        @Override
        protected LoginProvider create() {
            return new LoginProvider();
        }
    };
}
