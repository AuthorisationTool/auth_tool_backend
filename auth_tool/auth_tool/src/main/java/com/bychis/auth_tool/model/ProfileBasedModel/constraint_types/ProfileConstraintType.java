package com.bychis.auth_tool.model.ProfileBasedModel.constraint_types;

import com.bychis.auth_tool.rules.Value;

public interface ProfileConstraintType {
    String getProfileConstraintTypeName();
    boolean evaluateValue(String value);
}
