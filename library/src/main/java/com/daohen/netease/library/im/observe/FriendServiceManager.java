package com.daohen.netease.library.im.observe;

import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.FriendServiceObserve;
import com.netease.nimlib.sdk.friend.constant.FriendFieldEnum;
import com.netease.nimlib.sdk.friend.model.AddFriendData;

import java.util.Map;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 14:30
 */
public class FriendServiceManager {

    public static FriendServiceManager get(){
        return gDefault.get();
    }

    public void addFriend(AddFriendData data, NeteaseCallback<Void> callback){
        friendService.addFriend(data).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void ackAddFriendRequest(String account, boolean agree, NeteaseCallback<Void> callback){
        friendService.ackAddFriendRequest(account, agree).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void deleteFriend(String account, NeteaseCallback<Void> callback){
        friendService.deleteFriend(account).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateFriendFields(String friendAccount, Map<FriendFieldEnum, Object> fields, NeteaseCallback<Void> callback){
        friendService.updateFriendFields(friendAccount, fields).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void addToBlackList(String account, NeteaseCallback<Void> callback){
        friendService.addToBlackList(account).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void removeFromBlackList(String account, NeteaseCallback<Void> callback){
        friendService.removeFromBlackList(account).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void setMessageNotify(String account, boolean notify, NeteaseCallback<Void> callback){
        friendService.setMessageNotify(account, notify).setCallback(AbstractRequestCallback.getDefault(callback));
    }



    public FriendService getFriendService(){
        return friendService;
    }

    public FriendServiceObserve getFriendServiceObserve(){
        return friendServiceObserve;
    }

    private FriendService friendService;
    private FriendServiceObserve friendServiceObserve;

    private FriendServiceManager(){
        friendService = NIMClient.getService(FriendService.class);
        friendServiceObserve = NIMClient.getService(FriendServiceObserve.class);
    }

    private static final Singleton<FriendServiceManager> gDefault = new Singleton<FriendServiceManager>() {
        @Override
        protected FriendServiceManager create() {
            return new FriendServiceManager();
        }
    };
}
