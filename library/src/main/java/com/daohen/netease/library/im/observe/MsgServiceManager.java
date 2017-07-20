package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 11:28
 */
public class MsgServiceManager {

    public static MsgServiceManager get(){
        return gDefault.get();
    }

    public void observeMsgStatus(){
        msgServiceObserve.observeMsgStatus(new Observer<IMMessage>() {
            @Override
            public void onEvent(IMMessage imMessage) {
                // TODO: 17/7/20 待实现
            }
        }, true);
    }

    private MsgServiceObserve msgServiceObserve;

    private MsgServiceManager(){
        msgServiceObserve = NIMClient.getService(MsgServiceObserve.class);
    }

    private static final Singleton<MsgServiceManager> gDefault = new Singleton<MsgServiceManager>() {
        @Override
        protected MsgServiceManager create() {
            return new MsgServiceManager();
        }
    };
}
