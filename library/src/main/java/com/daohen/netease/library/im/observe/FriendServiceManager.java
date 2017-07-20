package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.friend.FriendService;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 14:30
 */
public class FriendServiceManager {

    public static FriendServiceManager get(){
        return gDefault.get();
    }

    public void setMessageNotify(String account, boolean notify){
        friendService.setMessageNotify(account, notify);
    }

    private FriendService friendService;

    private FriendServiceManager(){
        friendService = NIMClient.getService(FriendService.class);
    }

    private static final Singleton<FriendServiceManager> gDefault = new Singleton<FriendServiceManager>() {
        @Override
        protected FriendServiceManager create() {
            return new FriendServiceManager();
        }
    };
}
