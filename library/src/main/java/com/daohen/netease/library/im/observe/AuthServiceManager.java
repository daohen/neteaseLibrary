package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Logs;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;

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
                if (statusCode.wontAutoLogin()){

                }
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
