package com.bychis.auth_tool.service;
import com.bychis.auth_tool.api.request_holders.ConstraintRequestHolder;
import com.bychis.auth_tool.dao.ConstraintDao;
import com.bychis.auth_tool.dao.ConstraintDaoImpl;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.model.ContextBasedModel.constraint_types.EnvironmentConstraintType;
import com.bychis.auth_tool.model.ContextBasedModel.model_structure.EnvironmentConstraint;
import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.ProfileConstraintType;
import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConstraintService {

    private final ConstraintDao constraintDao;

    @Autowired
    public ConstraintService(@Qualifier("constraintDao") ConstraintDao constraintDao){
        this.constraintDao = constraintDao;
    }

    public void createConstraint(String roleID, int levelID, ConstraintRequestHolder constraintRequestHolder){
        Constraint constraint = constraintRequestHolder.forgeConstraint();
        this.constraintDao.createConstraint(roleID,levelID,constraint);
    }

    public void deleteConstraint(String roleID, int levelID, int constraintID){
        this.constraintDao.deleteConstraint(roleID,levelID,constraintID);
    }

    public void updateConstraint(String roleID, int levelID, int constraintID,
                                 ConstraintRequestHolder constraintRequestHolder){
        Constraint constraint = constraintRequestHolder.forgeConstraint();
        this.constraintDao.updateConstraint(roleID,levelID,constraintID,constraint);
    }

    public Constraint getConstraintByID(String roleID, int levelID, int constraintID){
        return this.constraintDao.getConstraintByID(roleID,levelID,constraintID);
    }

    public List<Constraint> getConstraintsAll(String roleID, int levelID){
        return this.constraintDao.getConstraintsAll(roleID,levelID);
    }

    public static List<ProfileConstraintType> getProfileConstraints(String roleID, int levelID){
        List<Constraint> constraints = ConstraintDaoImpl.getConstraints(roleID, levelID);
        List<ProfileConstraintType> profileConstraintTypes =
                new ArrayList<>();
        for(Constraint constraint: constraints){
            ProfileConstraint p = (ProfileConstraint)constraint;
            profileConstraintTypes.add(p.getProfileConstraintType());
        }
        return profileConstraintTypes;
    }

    public static List<EnvironmentConstraintType> getEnvironmentConstraints(String roleID, int levelID){
        List<Constraint> constraints = ConstraintDaoImpl.getConstraints(roleID,levelID);
        List<EnvironmentConstraintType> environmentConstraintTypes =
                new ArrayList<>();
        for(Constraint constraint: constraints){
            EnvironmentConstraint p = (EnvironmentConstraint)constraint;
            environmentConstraintTypes.add(((EnvironmentConstraint) constraint).getConstraintType());
        }
        return environmentConstraintTypes;
    }

    public List<String> getTypeOfConstraintToDemand(String roleID, int levelID){
        List<Constraint> constraintList = this.getConstraintsAll(roleID,levelID);
        List<String> typeconstraints = new ArrayList<>();
        for(Constraint constraint : constraintList){
            typeconstraints.add(constraint.getConstraintTypeSpecificName());
        }
        return typeconstraints;
    }


}
