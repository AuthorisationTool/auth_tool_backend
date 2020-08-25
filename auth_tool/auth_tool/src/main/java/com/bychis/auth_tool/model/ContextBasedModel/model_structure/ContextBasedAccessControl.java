package com.bychis.auth_tool.model.ContextBasedModel.model_structure;

import com.bychis.auth_tool.model.Common.AccessControlModel;

import java.util.ArrayList;
import java.util.List;


public class ContextBasedAccessControl implements AccessControlModel {

    private List<EnvironmentConstraint> constraintList;

    public ContextBasedAccessControl(){
        this.constraintList = new ArrayList<>();
    }

    public ContextBasedAccessControl(List<EnvironmentConstraint> constraintList) {
        this.constraintList = constraintList;
    }

    public List<EnvironmentConstraint> getConstraintList() {
        return constraintList;
    }

    public void setConstraintList(List<EnvironmentConstraint> constraintList) {
        this.constraintList = constraintList;
    }

    @Override
    public String getModelName() {
        return this.getClass().getSimpleName();
    }
}
