package com.bychis.auth_tool.rules;

import com.bychis.auth_tool.model.ContextBasedModel.constraint_types.EnvironmentConstraintType;
import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.ProfileConstraintType;
import java.util.List;

public class Value {

    private String type;
    private String arg;

    public Value(String type, String arg) {
        this.type = type;
        this.arg = arg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public static boolean isValidProfileValue(Value value, List<ProfileConstraintType> constraints){
        boolean result = false;
        loop: for (ProfileConstraintType constraint: constraints){
            if(value.getType().equals(constraint.getProfileConstraintTypeName())){
               result = constraint.evaluateValue(value.getArg());
               if(!result) break loop;
            }
        }
        return result;
    }

    public static boolean isValidEnvironmentValue(Value value, List<EnvironmentConstraintType> constraints){
        boolean result = false;
        loop: for (EnvironmentConstraintType constraint: constraints){
            if(value.getType().equals(constraint.getEnvironmentConstraintTypeName())){
                result = constraint.evaluateValue(value.getArg());
                if(!result) break loop;
            }
        }
        return result;

    }
}
