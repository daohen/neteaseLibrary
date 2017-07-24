package com.daohen.netease.library.im.observe;

import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.SystemMessage;

import java.util.List;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/24 17:36
 */
public class SystemMessageServiceManager {

    public static SystemMessageServiceManager get(){
        return gDefault.get();
    }

    public void querySystemMessages(int offset, int limit, NeteaseCallback<List<SystemMessage>> callback){
        systemMessageService.querySystemMessages(offset, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void querySystemMessageByType(List<SystemMessageType> types, int offset, int limit, NeteaseCallback<List<SystemMessage>> callback){
        systemMessageService.querySystemMessageByType(types, offset, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void querySystemMessageUnread(NeteaseCallback<List<SystemMessage>> callback){
        systemMessageService.querySystemMessageUnread().setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void querySystemMessageUnreadCount(NeteaseCallback<Integer> callback){
        systemMessageService.querySystemMessageUnreadCount().setCallback(AbstractRequestCallback.getDefault(callback));
    }


    public SystemMessageService getSystemMessageService(){
        return systemMessageService;
    }

    public SystemMessageObserver getSystemMessageObserver(){
        return systemMessageObserver;
    }

    private SystemMessageService systemMessageService;
    private SystemMessageObserver systemMessageObserver;

    private SystemMessageServiceManager(){
        systemMessageService = NIMClient.getService(SystemMessageService.class);
        systemMessageObserver = NIMClient.getService(SystemMessageObserver.class);
    }

    private static final Singleton<SystemMessageServiceManager> gDefault = new Singleton<SystemMessageServiceManager>() {
        @Override
        protected SystemMessageServiceManager create() {
            return new SystemMessageServiceManager();
        }
    };
}
