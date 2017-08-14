package com.daohen.netease.library.manager;

import com.daohen.netease.library.callback.AbstractObserver;
import com.daohen.netease.library.callback.AbstractRequestCallback;
import com.daohen.netease.library.callback.NeteaseCallback;
import com.daohen.netease.library.tool.NeteasePreferences;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.OnlineClient;

/**
 * Created by alun on 17/7/19.
 */

public class AuthServiceManager {

    public static AuthServiceManager get(){
        return gDefault.get();
    }

    public void observeOnlineStatus(Observer<StatusCode> observer){
        authServiceObserver.observeOnlineStatus(new AbstractObserver<StatusCode>(observer) {
            @Override
            public void onEventBefore(StatusCode statusCode) {
                if (statusCode.wontAutoLogin()){
                    NeteasePreferences.get().clear();
                }
            }
        }, true);
    }

    public void login(LoginInfo info, final NeteaseCallback<LoginInfo> callback){
        authService.login(info).setCallback(new AbstractRequestCallback<LoginInfo>(callback) {
            @Override
            public void onSuccessBefore(LoginInfo param) {
                NeteasePreferences.get().setAccount(param.getAccount());
                NeteasePreferences.get().setToken(param.getToken());
            }
        });
    }

    public void kickOtherClient(OnlineClient client, NeteaseCallback<Void> callback){
        authService.kickOtherClient(client).setCallback(AbstractRequestCallback.getDefault(callback));
    }


    public AuthService getAuthService(){
        return authService;
    }

    public AuthServiceObserver getAuthServiceObserver(){
        return authServiceObserver;
    }

    private AuthServiceObserver authServiceObserver;
    private AuthService authService;

    private AuthServiceManager(){
        authServiceObserver = NIMClient.getService(AuthServiceObserver.class);
        authService = NIMClient.getService(AuthService.class);
    }

    private static final Singleton<AuthServiceManager> gDefault = new Singleton<AuthServiceManager>() {
        @Override
        protected AuthServiceManager create() {
            return new AuthServiceManager();
        }
    };
}
