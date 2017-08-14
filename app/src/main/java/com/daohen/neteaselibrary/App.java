package com.daohen.neteaselibrary;

import android.app.Application;

import com.daohen.netease.library.NeteaseConnect;
import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Files;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/**
 * Created by alun on 17/7/18.
 */

public class App extends Application {

    private String appkey = "8ff9b36e20954ab45ea4a5ee9692ec5e";
    private String appSecret = "039bd669cb5d";

    @Override
    public void onCreate() {
        super.onCreate();

        Contexts.setContext(this);

        NeteaseConnect.get().register(appkey)
                .preloadAttach(true)
                .sdkStorageRootPath(Files.getFilesPath())
                .sessionReadAck(true)
                .statusBarNotificationConfig(new StatusBarNotificationConfig())
                .thumbnailSize(Contexts.getScreenWidth() / 3)
                .init();



    }
}
