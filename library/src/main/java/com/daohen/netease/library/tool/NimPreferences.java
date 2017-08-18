package com.daohen.netease.library.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;

/**
 * Created by alun on 17/7/18.
 */

public class NimPreferences {

    public static NimPreferences get(){
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

    private NimPreferences(){
        preferences = Contexts.getContext().getSharedPreferences("netease", Context.MODE_PRIVATE);
    }

    private static final Singleton<NimPreferences> gDefault = new Singleton<NimPreferences>() {
        @Override
        protected NimPreferences create() {
            return new NimPreferences();
        }
    };

}
