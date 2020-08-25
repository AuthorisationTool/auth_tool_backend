package com.bychis.auth_tool.model.Common;

public class Level {

    private int id;
    private AccessControlModel acm;

    public Level(int id, AccessControlModel acm) {
        this.id = id;
        this.acm = acm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccessControlModel getAcm() {
        return acm;
    }

    public void setAcm(AccessControlModel acm) {
        this.acm = acm;
    }
}
