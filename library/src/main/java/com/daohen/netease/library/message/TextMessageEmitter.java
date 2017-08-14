package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/20 00:50
 */
public class TextMessageEmitter extends MessageEmitter {

    public TextMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @param text 文本内容
         * @return
         */
        public TextMessageEmitter build(String sessionId, SessionTypeEnum sessionType, String text){
            IMMessage message = MessageBuilder.createTextMessage(sessionId, sessionType, text);
            return new TextMessageEmitter(message);
        }

    }
}
