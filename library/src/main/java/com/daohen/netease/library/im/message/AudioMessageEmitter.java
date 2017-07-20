package com.daohen.netease.library.im.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 10:45
 */
public class AudioMessageEmitter extends MessageEmitter {

    public AudioMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @param file 音频文件
         * @param duration 音频持续时间，单位是ms
         * @return
         */
        public AudioMessageEmitter build(String sessionId, SessionTypeEnum sessionType, File file, long duration){
            IMMessage imMessage = MessageBuilder.createAudioMessage(sessionId, sessionType, file, duration);
            return new AudioMessageEmitter(imMessage);
        }

    }
}
