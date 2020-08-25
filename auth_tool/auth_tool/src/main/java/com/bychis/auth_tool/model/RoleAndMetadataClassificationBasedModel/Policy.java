package com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel;


import java.util.List;
import java.util.UUID;


public class Policy {

    private String PolicyID;
    private List<Role> roleList;

    public Policy() {
        PolicyID = UUID.randomUUID().toString();
    }

    public String getPolicyID() {
        return PolicyID;
    }

    public void setPolicyID(String policyID) {
        PolicyID = policyID;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
