package com.bychis.auth_tool.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Decision {

    private String id;
    private String ip_address;
    private String user;
    private String roleID;
    private List<String> demanded_values = new ArrayList<>();
    private String typeOfRequestToDemand;
    private RequestState requestState = RequestState.PRE_EVALUATION;
    private String message;

    public Decision() {
    }


    public String getTypeOfRequestToDemand() {
        return typeOfRequestToDemand;
    }

    public void setTypeOfRequestToDemand(String typeOfRequestToDemand) {
        this.typeOfRequestToDemand = typeOfRequestToDemand;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Decision(String user, RequestState requestState) {
        this.user = user;
        this.requestState = requestState;
        this.id = UUID.randomUUID().toString();
    }

    public Decision(RequestState requestState, String message) {
        this.requestState = requestState;
        this.message = message;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getDemanded_values() {
        return demanded_values;
    }

    public void setDemanded_values(List<String > demanded_values) {
        this.demanded_values = demanded_values;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    public void grantAccess(){
        if(this.requestState.equals(RequestState.GRANTED)){
            // do linux command here
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
