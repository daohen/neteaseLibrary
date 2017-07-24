package com.daohen.netease.library.im.observe;

import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.search.model.MsgIndexRecord;

import java.util.List;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 11:28
 */
public class MsgServiceManager {

    public static MsgServiceManager get(){
        return gDefault.get();
    }

    public void sendMessage(IMMessage msg, boolean resend, NeteaseCallback<Void> callback){
        msgService.sendMessage(msg, resend).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void saveMessageToLocal(IMMessage msg, boolean notify, NeteaseCallback<Void> callback){
        msgService.saveMessageToLocal(msg, notify).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void saveMessageToLocalEx(IMMessage msg, boolean notify, long time, NeteaseCallback<Void> callback){
        msgService.saveMessageToLocalEx(msg, notify, time).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMessageListByUuid(List<String> uuids, NeteaseCallback<List<IMMessage>> callback){
        msgService.queryMessageListByUuid(uuids).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage anchor, int limit, NeteaseCallback<List<IMMessage>> callback){
        msgService.queryMessageListByType(msgTypeEnum, anchor, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMessageList(String account, SessionTypeEnum sessionType, long offset, int limit, NeteaseCallback<List<IMMessage>> callback){
        msgService.queryMessageList(account, sessionType, offset, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMessageListEx(IMMessage anchor, QueryDirectionEnum direction, int limit, boolean asc, NeteaseCallback<List<IMMessage>> callback){
        msgService.queryMessageListEx(anchor, direction, limit, asc).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMessageListExTime(IMMessage anchor, long toTime, QueryDirectionEnum direction, int limit, NeteaseCallback<List<IMMessage>> callback){
        msgService.queryMessageListExTime(anchor, toTime, direction, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void pullMessageHistory(IMMessage anchor, int limit, boolean persist, NeteaseCallback<List<IMMessage>> callback){
        msgService.pullMessageHistory(anchor, limit, persist).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void pullMessageHistoryEx(IMMessage anchor, long toTime, int limit, QueryDirectionEnum direction, boolean persist, NeteaseCallback<List<IMMessage>> callback){
        msgService.pullMessageHistoryEx(anchor, toTime, limit, direction, persist).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void searchMessageHistory(String keyword, List<String> fromAccounts, IMMessage anchor, int limit, NeteaseCallback<List<IMMessage>> callback){
        msgService.searchMessageHistory(keyword, fromAccounts, anchor, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void searchAllMessageHistory(String keyword, List<String> fromAccounts, long time, int limit, NeteaseCallback<List<IMMessage>> callback){
        msgService.searchAllMessageHistory(keyword, fromAccounts, time, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void searchAllSession(String query, int limit, NeteaseCallback<List<MsgIndexRecord>> callback){
        msgService.searchAllSession(query, limit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void searchSession(String query, SessionTypeEnum sessionType, String sessionId, NeteaseCallback<List<MsgIndexRecord>> callback){
        msgService.searchSession(query, sessionType, sessionId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void sendMessageReceipt(String sessionId, IMMessage message, NeteaseCallback<Void> callback){
        msgService.sendMessageReceipt(sessionId, message).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void sendCustomNotification(CustomNotification notification, NeteaseCallback<Void> callback){
        msgService.sendCustomNotification(notification).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryRecentContacts(NeteaseCallback<List<RecentContact>> callback){
        msgService.queryRecentContacts().setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void deleteRoamingRecentContact(String contactId, SessionTypeEnum sessionTypeEnum, NeteaseCallback<Void> callback){
        msgService.deleteRoamingRecentContact(contactId, sessionTypeEnum).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void revokeMessage(IMMessage message, NeteaseCallback<Void> callback){
        msgService.revokeMessage(message).setCallback(AbstractRequestCallback.getDefault(callback));
    }


    public MsgService getMsgService(){
        return msgService;
    }

    public MsgServiceObserve getMsgServiceObserve(){
        return msgServiceObserve;
    }

    private MsgServiceObserve msgServiceObserve;
    private MsgService msgService;

    private MsgServiceManager(){
        msgServiceObserve = NIMClient.getService(MsgServiceObserve.class);
        msgService = NIMClient.getService(MsgService.class);
    }

    private static final Singleton<MsgServiceManager> gDefault = new Singleton<MsgServiceManager>() {
        @Override
        protected MsgServiceManager create() {
            return new MsgServiceManager();
        }
    };
}
