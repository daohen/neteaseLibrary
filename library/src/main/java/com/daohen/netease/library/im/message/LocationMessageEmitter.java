package com.daohen.netease.library.im.message;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 09:50
 */
public class LocationMessageEmitter extends MessageEmitter {

    public LocationMessageEmitter(IMMessage imMessage) {
        super(imMessage);
    }

    public static class Builder{

        /**
         * @param sessionId 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
         * @param sessionType 聊天类型，单聊或群组
         * @param lat 纬度
         * @param lng 经度
         * @param addr 地址信息描述
         * @return
         */
        public LocationMessageEmitter build(String sessionId, SessionTypeEnum sessionType, double lat, double lng, String addr){
            IMMessage imMessage = MessageBuilder.createLocationMessage(sessionId, sessionType, lat, lng, addr);
            return new LocationMessageEmitter(imMessage);
        }

    }
}
