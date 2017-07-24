package com.daohen.netease.library.im.observe;

import com.daohen.netease.library.im.callback.AbstractObserver;
import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.netease.library.im.tool.NeteasePreferences;
import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Logs;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.OnlineClient;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;

import java.util.List;

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

    public void observeLoginSyncDataStatus(Observer<LoginSyncStatus> observer){
        authServiceObserver.observeLoginSyncDataStatus(AbstractObserver.getDefault(observer), true);
    }

    public void observeOtherClients(Observer<List<OnlineClient>> observer){
        authServiceObserver.observeOtherClients(AbstractObserver.getDefault(observer), true);
    }


    public void logout(){
        authService.logout();
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
