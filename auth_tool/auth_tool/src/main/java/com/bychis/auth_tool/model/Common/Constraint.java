package com.bychis.auth_tool.model.Common;

import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileConstraint;

public interface Constraint {

    String getConstraintTypeName();
    String getConstraintTypeSpecificName();
    String getConstraintArg();
    Action getAction();
    Permission getPermission();
    int getConstraintID();
}
