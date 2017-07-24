package com.daohen.netease.library.im.manager;

import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.UserServiceObserve;
import com.netease.nimlib.sdk.uinfo.constant.UserInfoFieldEnum;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.List;
import java.util.Map;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/24 18:19
 */
public class UserServiceManager {

    public static UserServiceManager get(){
        return gDefault.get();
    }

    public void fetchUserInfo(List<String> accounts, NeteaseCallback<List<NimUserInfo>> callback){
        userService.fetchUserInfo(accounts).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateUserInfo(Map<UserInfoFieldEnum, Object> fields, NeteaseCallback<Void> callback){
        userService.updateUserInfo(fields).setCallback(AbstractRequestCallback.getDefault(callback));
    }


    public UserService getUserService(){
        return userService;
    }

    public UserServiceObserve getUserServiceObserve(){
        return userServiceObserve;
    }

    private UserService userService;
    private UserServiceObserve userServiceObserve;

    private UserServiceManager(){
        userService = NIMClient.getService(UserService.class);
        userServiceObserve = NIMClient.getService(UserServiceObserve.class);
    }

    private static final Singleton<UserServiceManager> gDefault = new Singleton<UserServiceManager>() {
        @Override
        protected UserServiceManager create() {
            return new UserServiceManager();
        }
    };
}
