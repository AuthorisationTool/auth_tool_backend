package com.bychis.auth_tool.api.request_holders;

public class BadgeEvaluationRequestHolder {
    private String bid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public BadgeEvaluationRequestHolder(String bid) {
        this.bid = bid;
    }

    public BadgeEvaluationRequestHolder() {
    }
}
