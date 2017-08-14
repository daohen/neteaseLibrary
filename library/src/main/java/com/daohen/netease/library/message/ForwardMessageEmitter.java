package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 14:10
 */
public class ForwardMessageEmitter extends MessageEmitter {

    public ForwardMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        public ForwardMessageEmitter build(IMMessage message, String sessionId, SessionTypeEnum sessionType){
            IMMessage imMessage = MessageBuilder.createForwardMessage(message, sessionId, sessionType);
            return new ForwardMessageEmitter(imMessage);
        }

    }
}
