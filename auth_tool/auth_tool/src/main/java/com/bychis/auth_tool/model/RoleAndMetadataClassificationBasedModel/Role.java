package com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel;

import com.bychis.auth_tool.model.Common.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Role {

    private String roleId;
    private String roleName;
    private List<Level> levelList;
    private RessourcesSpecificConstraintList rscl;

    public Role(String roleName,String classification,String classificationArg){
        this.roleName = roleName;
        this.roleId = UUID.randomUUID().toString();
        this.levelList = new ArrayList<>();

        switch (classification){
            case "type":
                this.rscl = new RessourcesSpecificConstraintList(Classification.type,classificationArg);
                break;
            case "category":
                this.rscl = new RessourcesSpecificConstraintList(Classification.category,classificationArg);
                break;
            case "sensitivity":
                this.rscl = new RessourcesSpecificConstraintList(Classification.sensitivity,classificationArg);
                break;
        }


    }
    public Role() {
        this.roleId = UUID.randomUUID().toString();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public RessourcesSpecificConstraintList getRscl() {
        return rscl;
    }

    public void setRscl(RessourcesSpecificConstraintList rscl) {
        this.rscl = rscl;
    }
}
