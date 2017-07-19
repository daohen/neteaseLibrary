package com.daohen.netease.library.im.tool;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Strings;

/**
 * Created by alun on 17/7/18.
 */

public class Utils {

    public static boolean inMainProcess() {
        String packageName = Contexts.getContext().getPackageName();
        String processName = getProcessName(Contexts.getContext());
        return packageName.equals(processName);
    }

    public static final String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }

            // go home
            if (!Strings.isNull(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
