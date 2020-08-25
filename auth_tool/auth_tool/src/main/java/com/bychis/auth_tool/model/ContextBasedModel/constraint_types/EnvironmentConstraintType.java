package com.bychis.auth_tool.model.ContextBasedModel.constraint_types;

public interface EnvironmentConstraintType {
    String getEnvironmentConstraintTypeName();
    boolean evaluateValue(String value);
}
