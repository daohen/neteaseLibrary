package com.daohen.netease.library.im;

import com.daohen.netease.library.im.listener.NeteaseLoginListener;
import com.daohen.netease.library.im.tool.NLPreferences;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * Created by alun on 17/7/18.
 */

public class LoginProvider {

    public static LoginProvider get(){
        return gDefault.get();
    }

    private AbortableFuture<LoginInfo> loginInfoAbortableFuture;

    /**
     * 手动登陆
     * @param info
     * @param listener
     */
    public void login(LoginInfo info, final NeteaseLoginListener listener){
        loginInfoAbortableFuture = authService.login(info);
        loginInfoAbortableFuture.setCallback(new RequestCallbackWrapper<LoginInfo>() {
            @Override
            public void onResult(int code, LoginInfo result, Throwable exception) {
                switch (code){
                    case ResponseCode.RES_SUCCESS:
                        NLPreferences.get().setAccount(result.getAccount());
                        NLPreferences.get().setToken(result.getToken());

                        listener.onSuccess(result);
                        break;
                    case ResponseCode.RES_EXCEPTION:
                        listener.onFail(exception);
                        break;
                    default:
                        listener.onFail(new Throwable("请求失败"));
                        break;
                }
            }
        });
    }

    /**
     * 取消手动登陆
     * @return
     */
    public boolean unLogin(){
        return loginInfoAbortableFuture == null ? false : loginInfoAbortableFuture.abort();
    }



    public StatusCode getCurrentStatus(){
        return NIMClient.getStatus();
    }

    private AuthService authService;

    private LoginProvider(){
        authService = NIMClient.getService(AuthService.class);
    }

    private static final Singleton<LoginProvider> gDefault = new Singleton<LoginProvider>() {
        @Override
        protected LoginProvider create() {
            return new LoginProvider();
        }
    };
}
