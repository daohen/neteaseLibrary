package com.daohen.netease.library.im.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;

/**
 * Created by alun on 17/7/18.
 */

public class NLPreferences {

    public static NLPreferences get(){
        return gDefault.get();
    }

    public void setAccount(String account){
        preferences.edit().putString("account", account).apply();
    }

    public String getAccount(){
        return preferences.getString("account", null);
    }

    public void setToken(String token){
        preferences.edit().putString("token", token).apply();
    }

    public String getToken(){
        return preferences.getString("token", null);
    }

    public void clear(){
        preferences.edit().clear().apply();
    }

    private SharedPreferences preferences;

    private NLPreferences(){
        preferences = Contexts.getContext().getSharedPreferences("netease", Context.MODE_PRIVATE);
    }

    private static final Singleton<NLPreferences> gDefault = new Singleton<NLPreferences>() {
        @Override
        protected NLPreferences create() {
            return new NLPreferences();
        }
    };

}
