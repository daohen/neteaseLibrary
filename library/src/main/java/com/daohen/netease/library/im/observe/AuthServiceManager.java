package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Logs;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
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

    public void observeOnlineStatus(){
        authServiceObserver.observeOnlineStatus(new Observer<StatusCode>() {
            @Override
            public void onEvent(StatusCode statusCode) {
                Logs.d("AuthServiceManager "+statusCode.getValue());
                // TODO: 17/7/19 退出登陆需要处理的操作 及 退出后和界面相关要做的处理 
                // TODO: 17/7/19 被踢掉的处理
            }
        }, true);
    }

    public void observeLoginSyncDataStatus(){
        authServiceObserver.observeLoginSyncDataStatus(new Observer<LoginSyncStatus>() {
            @Override
            public void onEvent(LoginSyncStatus loginSyncStatus) {
                Logs.d("observeLoginSyncDataStatus "+loginSyncStatus.name());
                // TODO: 17/7/19 待处理
            }
        }, true);
    }

    public void observeOtherClients(){
        authServiceObserver.observeOtherClients(new Observer<List<OnlineClient>>() {
            @Override
            public void onEvent(List<OnlineClient> onlineClients) {
                // TODO: 17/7/19 待处理
            }
        }, true);
    }


    private AuthServiceObserver authServiceObserver;

    private AuthServiceManager(){
        authServiceObserver = NIMClient.getService(AuthServiceObserver.class);
    }

    private static final Singleton<AuthServiceManager> gDefault = new Singleton<AuthServiceManager>() {
        @Override
        protected AuthServiceManager create() {
            return new AuthServiceManager();
        }
    };
}
