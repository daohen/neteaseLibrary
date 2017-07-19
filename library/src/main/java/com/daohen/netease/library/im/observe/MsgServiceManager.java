package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/20 00:29
 */
public class MsgServiceManager {

    public static MsgServiceManager get(){
        return gDefault.get();
    }

    /**
     * 发送文本消息
     * @param sessionId
     * @param sessionType
     * @param text
     * @param reSend
     */
    public void sendTextMessage(String sessionId, SessionTypeEnum sessionType, String text, boolean reSend, RequestCallbackWrapper<Void> callback){
        IMMessage message = MessageBuilder.createTextMessage(sessionId, sessionType, text);
        sendMessage(message, reSend, callback);
    }

    public void sendTextMessage(String sessionId, SessionTypeEnum sessionType, String text){
        sendTextMessage(sessionId, sessionType, text, false, null);
    }


    private void sendMessage(IMMessage message, boolean reSend, RequestCallbackWrapper<Void> callback){
        InvocationFuture<Void> invocationFuture = msgService.sendMessage(message, reSend);
        if (callback != null)
            invocationFuture.setCallback(callback);
    }

    private MsgService msgService;

    private MsgServiceManager(){
        msgService = NIMClient.getService(MsgService.class);
    }

    private static final Singleton<MsgServiceManager> gDefault = new Singleton<MsgServiceManager>() {
        @Override
        protected MsgServiceManager create() {
            return new MsgServiceManager();
        }
    };
}
