package com.daohen.netease.library;

import com.daohen.netease.library.manager.AuthServiceManager;
import com.daohen.netease.library.manager.MsgServiceManager;
import com.daohen.netease.library.manager.SystemMessageServiceManager;
import com.daohen.netease.library.manager.TeamServiceManager;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.OnlineClient;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.team.model.Team;

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

    private Observer<StatusCode> onlineStatusObserver;
    private Observer<LoginSyncStatus> loginSyncStatusObserver;
    private Observer<List<OnlineClient>> otherClientsObserver;
    private Observer<CustomNotification> customNotificationObserver;
    private Observer<SystemMessage> receiveSystemMsgObserver;
    private Observer<Integer> unreadCountChangeObserver;


    public NeteaseGlobalObserve addObserveOnlineStatus(Observer<StatusCode> observer){
        AuthServiceManager.get().observeOnlineStatus(onlineStatusObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveLoginSyncDataStatus(Observer<LoginSyncStatus> observer){
        AuthServiceManager.get().getAuthServiceObserver().observeLoginSyncDataStatus(loginSyncStatusObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveOtherClients(Observer<List<OnlineClient>> observer){
        AuthServiceManager.get().getAuthServiceObserver().observeOtherClients(otherClientsObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveCustomNotification(Observer<CustomNotification> observer){
        MsgServiceManager.get().getMsgServiceObserve().observeCustomNotification(customNotificationObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addRegisterCustomAttachmentParser(MsgAttachmentParser customParser){
        MsgServiceManager.get().getMsgService().registerCustomAttachmentParser(customParser);
        return this;
    }

    public NeteaseGlobalObserve addObserveReceiveSystemMsg(Observer<SystemMessage> observer){
        SystemMessageServiceManager.get().getSystemMessageObserver().observeReceiveSystemMsg(receiveSystemMsgObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addUnreadCountChange(Observer<Integer> observer){
        SystemMessageServiceManager.get().getSystemMessageObserver().observeUnreadCountChange(unreadCountChangeObserver = observer, true);
        return this;
    }

    public NeteaseGlobalObserve addObserveTeamRemove(Observer<Team> observer){
        TeamServiceManager.get().getTeamServiceObserver().observeTeamRemove(observer, true);
        return this;
    }


    private static final Singleton<NeteaseGlobalObserve> gDefault = new Singleton<NeteaseGlobalObserve>() {
        @Override
        protected NeteaseGlobalObserve create() {
            return new NeteaseGlobalObserve();
        }
    };
}
