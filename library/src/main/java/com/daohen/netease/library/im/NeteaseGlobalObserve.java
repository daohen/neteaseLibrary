package com.daohen.netease.library.im;

import com.daohen.netease.library.im.manager.AuthServiceManager;
import com.daohen.netease.library.im.manager.MsgServiceManager;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.OnlineClient;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;
import com.netease.nimlib.sdk.msg.model.CustomNotification;

import java.util.List;

/**
 * 注册一些全局的监听事件for netease，尽量放在Application之中，主线程之内
 *
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/24 12:00
 */
public class NeteaseGlobalObserve {

    public static NeteaseGlobalObserve get(){
        return gDefault.get();
    }

    public NeteaseGlobalObserve addObserveOnlineStatus(Observer<StatusCode> observer){
        AuthServiceManager.get().observeOnlineStatus(observer);
        return this;
    }

    public NeteaseGlobalObserve addObserveLoginSyncDataStatus(Observer<LoginSyncStatus> observer){
        AuthServiceManager.get().getAuthServiceObserver().observeLoginSyncDataStatus(observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveOtherClients(Observer<List<OnlineClient>> observer){
        AuthServiceManager.get().getAuthServiceObserver().observeOtherClients(observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveCustomNotification(Observer<CustomNotification> observer){
        MsgServiceManager.get().getMsgServiceObserve().observeCustomNotification(observer, true);
        return this;
    }

    public void addRegisterCustomAttachmentParser(MsgAttachmentParser customParser){
        MsgServiceManager.get().getMsgService().registerCustomAttachmentParser(customParser);
    }

    private static final Singleton<NeteaseGlobalObserve> gDefault = new Singleton<NeteaseGlobalObserve>() {
        @Override
        protected NeteaseGlobalObserve create() {
            return new NeteaseGlobalObserve();
        }
    };
}
