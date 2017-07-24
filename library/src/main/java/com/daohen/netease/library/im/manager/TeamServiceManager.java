package com.daohen.netease.library.im.manager;

import com.daohen.netease.library.im.callback.AbstractRequestCallback;
import com.daohen.netease.library.im.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.Singleton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.TeamServiceObserver;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/07/20 14:33
 */
public class TeamServiceManager {

    public static TeamServiceManager get(){
        return gDefault.get();
    }

    public void createTeam(Map<TeamFieldEnum, Serializable> fields, TeamTypeEnum type, String postscript, List<String> members, NeteaseCallback<Team> callback){
        teamService.createTeam(fields, type, postscript, members).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void addMembers(String teamId, List<String> accounts, NeteaseCallback<Void> callback){
        teamService.addMembers(teamId, accounts).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void removeMember(String teamId, String member, NeteaseCallback<Void> callback){
        teamService.removeMember(teamId, member).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void removeMembers(String teamId, List<String> members, NeteaseCallback<Void> callback){
        teamService.removeMembers(teamId, members).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateName(String teamId, String name, NeteaseCallback<Void> callback){
        teamService.updateName(teamId, name).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateTeam(String teamId, TeamFieldEnum field, Serializable value, NeteaseCallback<Void> callback){
        teamService.updateTeam(teamId, field, value).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateTeamFields(String teamId, Map<TeamFieldEnum, Serializable> fields, NeteaseCallback<Void> callback){
        teamService.updateTeamFields(teamId, fields).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void dismissTeam(String teamId, NeteaseCallback<Void> callback){
        teamService.dismissTeam(teamId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void quitTeam(String teamId, NeteaseCallback<Void> callback){
        teamService.quitTeam(teamId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryTeam(String teamId, NeteaseCallback<Team> callback){
        teamService.queryTeam(teamId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void searchTeam(String teamId, NeteaseCallback<Team> callback){
        teamService.searchTeam(teamId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryTeamList(NeteaseCallback<List<Team>> callback){
        teamService.queryTeamList().setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryTeamListById(List<String> tidList, NeteaseCallback<List<Team>> callback){
        teamService.queryTeamListById(tidList).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryTeamListByType(TeamTypeEnum type, NeteaseCallback<List<Team>> callback){
        teamService.queryTeamListByType(type).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void applyJoinTeam(String tid, String postscript, NeteaseCallback<Team> callback){
        teamService.applyJoinTeam(tid, postscript).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void passApply(String teamId, String account, NeteaseCallback<Void> callback){
        teamService.passApply(teamId, account).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void rejectApply(String teamId, String account, String reason, NeteaseCallback<Void> callback){
        teamService.rejectApply(teamId, account, reason).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void addManagers(String teamId, List<String> accounts, NeteaseCallback<List<TeamMember>> callback){
        teamService.addManagers(teamId, accounts).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void removeManagers(String teamId, List<String> managers, NeteaseCallback<List<TeamMember>> callback){
        teamService.removeManagers(teamId, managers).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void transferTeam(String tid, String account, boolean quit, NeteaseCallback<List<TeamMember>> callback){
        teamService.transferTeam(tid, account, quit).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void acceptInvite(String teamId, String inviter, NeteaseCallback<Void> callback){
        teamService.acceptInvite(teamId, inviter).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void declineInvite(String teamId, String inviter, String reason, NeteaseCallback<Void> callback){
        teamService.declineInvite(teamId, inviter, reason).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryMemberList(String teamId, NeteaseCallback<List<TeamMember>> callback){
        teamService.queryMemberList(teamId).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void queryTeamMember(String teamId, String account, NeteaseCallback<TeamMember> callback){
        teamService.queryTeamMember(teamId, account).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateMyTeamNick(String teamId, String nick, NeteaseCallback<Void> callback){
        teamService.updateMyTeamNick(teamId, nick).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateMemberNick(String teamId, String account, String nick, NeteaseCallback<Void> callback){
        teamService.updateMemberNick(teamId, account, nick).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void updateMyMemberExtension(String teamId, Map<String, Object> extension, NeteaseCallback<Void> callback){
        teamService.updateMyMemberExtension(teamId, extension).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void muteTeam(String teamId, boolean mute, NeteaseCallback<Void> callback){
        teamService.muteTeam(teamId, mute).setCallback(AbstractRequestCallback.getDefault(callback));
    }

    public void muteTeamMember(String teamId, String account, boolean mute, NeteaseCallback<Void> callback){
        teamService.muteTeamMember(teamId, account, mute).setCallback(AbstractRequestCallback.getDefault(callback));
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
