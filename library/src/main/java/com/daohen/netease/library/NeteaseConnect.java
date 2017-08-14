package com.daohen.netease.library;

import com.daohen.netease.library.tool.NeteasePreferences;
import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Strings;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.mixpush.NIMPushClient;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

/**
 * Created by alun on 17/7/18.
 */

public class NeteaseConnect {

    public static NeteaseConnect get(){
        return gDefault.get();
    }

    private String appkey;

    private SDKOptions sdkOptions;
    private LoginInfo loginInfo;

    public NeteaseConnect register(String appkey){
        this.appkey = appkey;
        sdkOptions.appKey = appkey;
        return this;
    }

    public NeteaseConnect statusBarNotificationConfig(StatusBarNotificationConfig config){
        sdkOptions.statusBarNotificationConfig = config;
        return this;
    }

    public NeteaseConnect userInfoProvider(UserInfoProvider userInfoProvider){
        sdkOptions.userInfoProvider = userInfoProvider;
        return this;
    }

    public NeteaseConnect thumbnailSize(int thumbnailSize){
        sdkOptions.thumbnailSize = thumbnailSize;
        return this;
    }

    public NeteaseConnect preloadAttach(boolean preloadAttach){
        sdkOptions.preloadAttach = preloadAttach;
        return this;
    }

    public NeteaseConnect sdkStorageRootPath(String sdkStorageRootPath){
        sdkOptions.sdkStorageRootPath = sdkStorageRootPath;
        return this;
    }

    public NeteaseConnect sessionReadAck(boolean sessionReadAck){
        sdkOptions.sessionReadAck = sessionReadAck;
        return this;
    }

    public NeteaseConnect messageNotifierCustomization(MessageNotifierCustomization messageNotifierCustomization){
        sdkOptions.messageNotifierCustomization = messageNotifierCustomization;
        return this;
    }

    public NeteaseConnect registerMiPush(String certificate, String appId, String appKey){
        NIMPushClient.registerMiPush(Contexts.getContext(), certificate, appId, appkey);
        return this;
    }

    public void init(){
        if (Strings.isNull(sdkOptions.appKey))
            throw new NullPointerException("NeteaseConnect里面的appkey没有设置，请调用register()方法");

        NIMClient.init(Contexts.getContext(), loginInfo, sdkOptions);
    }

    private NeteaseConnect(){
        sdkOptions = new SDKOptions();
        sdkOptions.databaseEncryptKey = "netease_daohen";
        if (!Strings.isNull(NeteasePreferences.get().getAccount()) && !Strings.isNull(NeteasePreferences.get().getToken())){
            loginInfo = new LoginInfo(NeteasePreferences.get().getAccount(), NeteasePreferences.get().getToken());
        }
    }

    private static final Singleton<NeteaseConnect> gDefault = new Singleton<NeteaseConnect>() {
        @Override
        protected NeteaseConnect create() {
            return new NeteaseConnect();
        }
    };
}
