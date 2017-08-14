package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 10:40
 */
public class ImageMessageEmitter extends MessageEmitter {

    public ImageMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @param file 图片文件对象
         * @param displayName 文件显示名字，如果第三方 APP 不关注，可以为 null
         * @return
         */
        public ImageMessageEmitter build(String sessionId, SessionTypeEnum sessionType, File file, String displayName){
            IMMessage imMessage = MessageBuilder.createImageMessage(sessionId, sessionType, file, displayName);
            return new ImageMessageEmitter(imMessage);
        }

        public ImageMessageEmitter build(String sessionId, SessionTypeEnum sessionType, File file){
            return build(sessionId, sessionType, file, null);
        }

    }
}
