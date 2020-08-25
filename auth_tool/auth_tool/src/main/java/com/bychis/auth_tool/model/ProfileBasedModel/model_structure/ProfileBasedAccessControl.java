package com.bychis.auth_tool.model.ProfileBasedModel.model_structure;

import com.bychis.auth_tool.model.Common.AccessControlModel;

import java.util.ArrayList;
import java.util.List;


public class ProfileBasedAccessControl implements AccessControlModel {

    private List<ProfileConstraint> constraintList;

    public ProfileBasedAccessControl(){
        this.constraintList = new ArrayList<>();
    }

    public ProfileBasedAccessControl(List<ProfileConstraint> list){
        this.constraintList = list;
    }

    public List<ProfileConstraint> getConstraintList() {
        return constraintList;
    }

    public void setConstraintList(List<ProfileConstraint> constraintList) {
        this.constraintList = constraintList;
    }

    @Override
    public String getModelName() {
        return this.getClass().getSimpleName();

    }
}
