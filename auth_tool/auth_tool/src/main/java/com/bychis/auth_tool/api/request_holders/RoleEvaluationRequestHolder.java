package com.bychis.auth_tool.api.request_holders;

public class RoleEvaluationRequestHolder {

    private String userID;
    private String requestID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public RoleEvaluationRequestHolder(String userID, String requestID) {
        this.userID = userID;
        this.requestID = requestID;
    }
}
