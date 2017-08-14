package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * 创建提醒消息
 * （主要用于会话内的通知提醒，例如进入会话时出现的欢迎消息，或是会话过程中命中敏感词后的提示消息等场景，
 * 也可以用自定义消息实现，但相对于Tip消息实现比较复杂）
 * 注意：提醒消息不支持setAttachment（如果要使用Attachment请使用自定义消息）。
 *
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 10:52
 */
public class TipMessageEmitter extends MessageEmitter {

    public TipMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @return
         */
        public TipMessageEmitter build(String sessionId, SessionTypeEnum sessionType){
            IMMessage imMessage = MessageBuilder.createTipMessage(sessionId, sessionType);
            return new TipMessageEmitter(imMessage);
        }

    }
}
