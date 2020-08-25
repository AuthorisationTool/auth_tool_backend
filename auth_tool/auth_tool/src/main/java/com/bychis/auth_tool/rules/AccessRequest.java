package com.bychis.auth_tool.rules;

import java.util.HashMap;
import java.util.List;

public class AccessRequest {
    private String typeOfRequest;
    private String requestID;
    private String bid;
    private String user;
    private String roleID;
    private int levelID;
    private String ip;
    private List<Value> valueList;
    private boolean evaluation = false;

    public AccessRequest(String typeOfRequest,String bid) {
        this.bid = bid;
        this.typeOfRequest = typeOfRequest;
    }

    public AccessRequest(String typeOfRequest, String requestID, String user) {
        this.typeOfRequest = typeOfRequest;
        this.requestID = requestID;
        this.user = user;
    }

    public AccessRequest(String requestID, List<Value> valueList) {
        this.requestID = requestID;
        this.valueList = valueList;
    }

    public String getRequestID() {
        return requestID;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUser() {
        return user;
    }

    public List<Value> getValueList() {
        return valueList;
    }

    public void setValueList(List<Value> valueList) {
        this.valueList = valueList;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
