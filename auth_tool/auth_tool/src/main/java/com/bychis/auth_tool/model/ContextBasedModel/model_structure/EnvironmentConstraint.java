package com.bychis.auth_tool.model.ContextBasedModel.model_structure;

import com.bychis.auth_tool.model.Common.Action;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.model.Common.Permission;
import com.bychis.auth_tool.model.ContextBasedModel.constraint_types.*;

import java.sql.Date;


public class EnvironmentConstraint implements Constraint {

    private int constraintID;
    private Action action;
    private EnvironmentConstraintType constraintType;
    private String constraintArg;
    private Permission permission;

    public EnvironmentConstraint(){
    }

    public EnvironmentConstraint(int constraintID, String permission, String environmentConstraintType, String constraintArg) {
        this.constraintID = constraintID;
        String[] splitStr = null;
        switch(permission){
            case "allow": this.permission = Permission.allow; break;
            case "deny": this.permission = Permission.deny; break;
            default: System.out.println("permission not defined");
        }
        switch (environmentConstraintType){
            case "TimeOfRequest":

                this.constraintType = new TimeOfRequest(Date.valueOf(constraintArg)); break;
            case "KindOfDevice": this.constraintType = new KindOfDevice(); break;
            case "AgentLocation":
                splitStr = constraintArg.split("\\s+");
                this.constraintType = new AgentLocation(Float.valueOf(splitStr[0]),
                        Float.valueOf(splitStr[1]));
                break;

            case "Gyro": splitStr = constraintArg.split("\\s+");
                         this.constraintType = new Gyro(Float.valueOf(splitStr[0]),
                                 Float.valueOf(splitStr[1]),
                                 Float.valueOf(splitStr[2]),
                                 Float.valueOf(splitStr[3]),
                                 Float.valueOf(splitStr[4]),
                                 Float.valueOf(splitStr[5])
                         );

            case "Acceleration": splitStr = constraintArg.split("\\s+");
                                this.constraintType = new Acceleration(
                                        Float.valueOf(splitStr[0]),
                                        Float.valueOf(splitStr[1]),
                                        Float.valueOf(splitStr[2]),
                                        Float.valueOf(splitStr[3]),
                                        Float.valueOf(splitStr[4]),
                                        Float.valueOf(splitStr[5])
                                );
                                break;

                                
            default: System.out.println("environment constraint type not defined");
        }
        this.constraintArg = constraintArg;
    }

    public EnvironmentConstraint(int constraintID, Action action, String environmentConstraintType, String constraintArg) {
        this.constraintID = constraintID;
        this.action = action;
        String[] splitStr = null;
        switch (environmentConstraintType){
            case "TimeOfRequest":

                this.constraintType = new TimeOfRequest(Date.valueOf(constraintArg)); break;
            case "KindOfDevice": this.constraintType = new KindOfDevice(); break;
            case "AgentLocation":
                splitStr = constraintArg.split("\\s+");
                this.constraintType = new AgentLocation(Float.valueOf(splitStr[0]),
                        Float.valueOf(splitStr[1]));
                break;

            case "Gyro": splitStr = constraintArg.split("\\s+");
                this.constraintType = new Gyro(Float.valueOf(splitStr[0]),
                        Float.valueOf(splitStr[1]),
                        Float.valueOf(splitStr[2]),
                        Float.valueOf(splitStr[3]),
                        Float.valueOf(splitStr[4]),
                        Float.valueOf(splitStr[5])
                );

            case "Acceleration": splitStr = constraintArg.split("\\s+");
                this.constraintType = new Acceleration(
                        Float.valueOf(splitStr[0]),
                        Float.valueOf(splitStr[1]),
                        Float.valueOf(splitStr[2]),
                        Float.valueOf(splitStr[3]),
                        Float.valueOf(splitStr[4]),
                        Float.valueOf(splitStr[5])
                );
                break;


            default: System.out.println("environment constraint type not defined");
        }
        this.constraintArg = constraintArg;
    }

    public EnvironmentConstraintType getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(EnvironmentConstraintType constraintType) {
        this.constraintType = constraintType;
    }

    @Override
    public String getConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getConstraintTypeSpecificName() {
        return this.constraintType.getClass().getSimpleName();
    }

    @Override
    public String getConstraintArg() {
        return constraintArg;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

    @Override
    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
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

    public void setConstraintArg(String constraintArg) {
        this.constraintArg = constraintArg;
    }
}
