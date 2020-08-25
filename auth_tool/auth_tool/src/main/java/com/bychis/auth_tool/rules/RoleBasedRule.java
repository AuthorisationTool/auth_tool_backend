package com.bychis.auth_tool.rules;
import com.bychis.auth_tool.service.ConstraintService;
import org.springframework.beans.factory.annotation.Autowired;


public class RoleBasedRule implements RuleI<AccessRequest,Decision> {

    private String roleID;


    public RoleBasedRule(String roleID) {
        this.roleID = roleID;
    }

    @Override
    public boolean matches(AccessRequest input) {
        boolean val = false;
        if(input.getTypeOfRequest().equals("role_evaluation")) {
            val = true;
            if(input.getRoleID().equals(this.roleID)){
                input.setEvaluation(true);
            }
        }
        return val;
    }

    @Override
    public Decision process(AccessRequest input) {
        Decision decision = null;
        if(input.isEvaluation()){
            decision = DecisionRepo.getDecisionByID(input.getRequestID());
            decision.setRoleID(input.getRoleID());
            decision.setTypeOfRequestToDemand("profile_evaluation");
            decision.setRequestState(RequestState.ON_EVALUATION);
            DecisionRepo.updateDecision(input.getRequestID(),decision);
        }else {
            decision = new Decision(RequestState.REJECTED,
                    "ACCESS DENIED BY LEVEL MODEL: Role Based Access Control ..." +
                            "You may contact your administrator for more info");
        }
        return decision;
    }
}
