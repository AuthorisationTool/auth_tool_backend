package com.bychis.auth_tool.rules;

import com.bychis.auth_tool.model.ProfileBasedModel.constraint_types.ProfileConstraintType;
import com.bychis.auth_tool.service.ConstraintService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfileBasedRule implements RuleI<AccessRequest,Decision> {
    private final String roleID;
    private final List<ProfileConstraintType> constraints;
    public ProfileBasedRule(String roleID, List<ProfileConstraintType> constraints) {
        this.roleID = roleID; this.constraints = constraints;
    }
    @Override
    public boolean matches(AccessRequest input) {
        input.setEvaluation(false);
        if(input.getTypeOfRequest().equals("profile_evaluation") &&
        input.getRoleID().equals(roleID)
        ){
            List<Value> values = input.getValueList();
            loop1: for(Value value: values){
                loop2: for(ProfileConstraintType constraint: constraints){
                    if(value.getType().equals(constraint.getProfileConstraintTypeName())) {
                        input.setEvaluation(constraint.evaluateValue(value.getArg()));
                        if (!input.isEvaluation()) break loop1;
                    } } } } return true;
    }
    @Override
    public Decision process(AccessRequest input) {
        Decision decision = null;
        if(input.isEvaluation()){
            decision = DecisionRepo.getDecisionByID(input.getRequestID());
            decision.setTypeOfRequestToDemand("environment_evaluation");
            DecisionRepo.updateDecision(input.getRequestID(),decision);
        }else{ decision = new Decision(RequestState.REJECTED, "ACCESS DENIED"); }
        return decision;
    }
}
