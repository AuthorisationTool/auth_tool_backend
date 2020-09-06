package com.bychis.auth_tool.model.ProfileBasedModel.constraint_types;

public class Mobility implements ProfileConstraintType{

    private String mobility;

    public Mobility(){}

    public Mobility(String mobility) {
        this.mobility = mobility;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    @Override
    public String getProfileConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {
        return this.getMobility().equals(value);
    }
}
