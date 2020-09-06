package com.bychis.auth_tool.model.ProfileBasedModel.model_structure;

import com.bychis.auth_tool.model.Common.Action;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.model.Common.Permission;
import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.Confidence;
import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.Mobility;
import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.ProfileConstraintType;

public class ProfileConstraint implements Constraint {

    private int constraintID;
    private Permission permission;
    private Action action;
    private ProfileConstraintType profileConstraintType;
    private String type_arg;

    public ProfileConstraint() {}

    public ProfileConstraint(int constraintID, String permission, String profileConstraintType, String type_arg) {
        this.constraintID = constraintID;
        switch (permission) {
            case "allow": this.permission = Permission.allow; break;
            case "deny": this.permission = Permission.deny; break;
        }
        switch (profileConstraintType) {
            case "Confidence":
                String[] splitStr = type_arg.split("\\s+");
                this.profileConstraintType = new Confidence(Integer.valueOf(splitStr[0]),
                        Integer.valueOf(splitStr[1]));
                break;
            case "Mobility": this.profileConstraintType = new Mobility(type_arg); break;
            default: System.out.println("profile constraint type not defined"); break;

        }
        this.type_arg = type_arg;
    }

    public ProfileConstraint(int constraintID, Action action, String profileConstraintType, String type_arg) {
        this.constraintID = constraintID;
        this.action = action;
        switch (profileConstraintType) {
            case "Confidence":
                String[] splitStr = type_arg.split("\\s+");
                this.profileConstraintType = new Confidence(Integer.valueOf(splitStr[0]),Integer.valueOf(splitStr[1]));

                break;
            case "Mobility": this.profileConstraintType = new Mobility(type_arg); break;
            default: System.out.println("profile constraint type not defined");
        }
        this.type_arg = type_arg;
    }

    public ProfileConstraintType getProfileConstraintType() {
        return profileConstraintType;
    }

    public void setProfileConstraintType(ProfileConstraintType profileConstraintType) {
        this.profileConstraintType = profileConstraintType;
    }

    public ProfileConstraintType getType() {
        return profileConstraintType;
    }

    public void setType(ProfileConstraintType type) {
        this.profileConstraintType = type;
    }

    public String getType_arg() {
        return type_arg;
    }

    public void setType_arg(String type_arg) {
        this.type_arg = type_arg;
    }

    @Override
    public String getConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getConstraintTypeSpecificName() {

        return this.profileConstraintType.getClass().getSimpleName();
    }

    @Override
    public String getConstraintArg() {
        return type_arg;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

    @Override
    public Permission getPermission() {
        return this.permission;
    }

    @Override
    public int getConstraintID() {
        return this.constraintID;
    }

    public void setConstraintID(int constraintID) {
        this.constraintID = constraintID;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
