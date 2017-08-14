package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/20 00:51
 */
public class MessageEmitter {

    private MsgService msgService;
    private IMMessage imMessage;

    public MessageEmitter(IMMessage imMessage){
        msgService = NIMClient.getService(MsgService.class);
        this.imMessage = imMessage;
    }

    public IMMessage getMessage(){
        return imMessage;
    }

    public void sendMessage(boolean resend, RequestCallbackWrapper<Void> callback){
        InvocationFuture<Void> invocationFuture = msgService.sendMessage(getMessage(), resend);
        if (callback != null)
            invocationFuture.setCallback(callback);
    }

    /**
     * 首次发送本消息，不关心回调
     */
    public void sendMessage(){
        sendMessage(false, null);
    }

    /**
     * 首次发送本消息，关心回调
     * @param callback
     */
    public void sendMessage(RequestCallbackWrapper<Void> callback){
        sendMessage(false, callback);
    }

    public void saveMessageToLocal(boolean notify, RequestCallbackWrapper<Void> callback){
        // TODO: 2017/7/20 其它相关对消息的操作实现
    }

}
