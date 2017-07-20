package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.AttachmentProgress;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;

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

    public void observeMsgStatus(){
        msgServiceObserve.observeMsgStatus(new Observer<IMMessage>() {
            @Override
            public void onEvent(IMMessage imMessage) {
                // TODO: 17/7/20 待实现
            }
        }, true);
    }

    public void observerAttachProgress(){
        msgServiceObserve.observeAttachmentProgress(new Observer<AttachmentProgress>() {
            @Override
            public void onEvent(AttachmentProgress attachmentProgress) {
                // TODO: 17/7/20 待实现
            }
        }, true);
    }

    public void observeReceiveMessage(){
        msgServiceObserve.observeReceiveMessage(new Observer<List<IMMessage>>() {
            @Override
            public void onEvent(List<IMMessage> imMessages) {
                // TODO: 17/7/20 待实现
            }
        }, true);
    }

    public void observeRecentContact(){
        msgServiceObserve.observeRecentContact(new Observer<List<RecentContact>>() {
            @Override
            public void onEvent(List<RecentContact> recentContacts) {
                // TODO: 17/7/20 待实现
            }
        }, true);
    }


    /**
     * 查询最近联系人列表数据
     */
    public void queryRecentContacts(){
        msgService.queryRecentContacts().setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
            @Override
            public void onResult(int code, List<RecentContact> result, Throwable exception) {
                // TODO: 17/7/20 待实现
            }
        });
    }

    /**
     * 获取所有未读数
     * @return
     */
    public int getTotalUnreadCount(){
        return msgService.getTotalUnreadCount();
    }

    /**
     * 未读数清零
     * @param account
     * @param sessionType
     */
    public void clearUnreadCount(String account, SessionTypeEnum sessionType){
        msgService.clearUnreadCount(account, sessionType);
    }

    public void deleteRecentContact(RecentContact recent){
        msgService.deleteRecentContact(recent);
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
