package com.daohen.netease.library.im.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/20 00:50
 */
public class TextMessageEmitter extends MessageEmitter {

    private IMMessage message;

    public TextMessageEmitter(IMMessage message){
        super();
        this.message = message;
    }

    @Override
    public IMMessage getMessage() {
        return message;
    }

    public static class Builder{

        public TextMessageEmitter build(String sessionId, SessionTypeEnum sessionType, String text){
            IMMessage message = MessageBuilder.createTextMessage(sessionId, sessionType, text);
            return new TextMessageEmitter(message);
        }

    }
}
