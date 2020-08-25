package com.bychis.auth_tool.model.ContextBasedModel.constraint_types;

public class KindOfDevice implements EnvironmentConstraintType{
    public KindOfDevice(){}
    @Override
    public String getEnvironmentConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {
        return true;
    }
}
