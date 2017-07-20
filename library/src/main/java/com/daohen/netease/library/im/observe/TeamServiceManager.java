package com.daohen.netease.library.im.observe;

import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.TeamServiceObserver;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 14:33
 */
public class TeamServiceManager {

    public static TeamServiceManager get(){
        return gDefault.get();
    }



    private TeamService teamService;
    private TeamServiceObserver teamServiceObserver;

    private TeamServiceManager(){
        teamService = NIMClient.getService(TeamService.class);
        teamServiceObserver = NIMClient.getService(TeamServiceObserver.class);
    }

    private static final Singleton<TeamServiceManager> gDefault = new Singleton<TeamServiceManager>() {
        @Override
        protected TeamServiceManager create() {
            return new TeamServiceManager();
        }
    };
}
