package com.daohen.netease.library.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 10:48
 */
public class VideoMessageEmitter extends MessageEmitter {

    public VideoMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @param file 视频文件
         * @param duration 视频持续时间
         * @param width 视频宽度
         * @param height 视频高度
         * @param displayName 视频显示名，可为空
         * @return
         */
        public VideoMessageEmitter build(String sessionId, SessionTypeEnum sessionType, File file, long duration, int width, int height, String displayName){
            IMMessage imMessage = MessageBuilder.createVideoMessage(sessionId, sessionType, file, duration, width, height, displayName);
            return new VideoMessageEmitter(imMessage);
        }

    }
}
