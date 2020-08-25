package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.Common.Constraint;

import java.util.List;

public interface ConstraintDao {

    void createConstraint(String roleID, int levelID, Constraint constraint);
    void deleteConstraint(String roleID, int levelID, int constraintID);
    List<Constraint> getConstraintsAll(String roleID, int levelID);
    Constraint getConstraintByID(String roleID, int levelID, int constraintID);
    void updateConstraint(String roleID, int levelID, int constraintID, Constraint constraint);
}
