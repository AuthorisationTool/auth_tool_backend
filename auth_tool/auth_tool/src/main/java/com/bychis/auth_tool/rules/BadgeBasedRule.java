package com.bychis.auth_tool.rules;

public class BadgeBasedRule implements RuleI<AccessRequest,Decision> {

    @Override
    public boolean matches(AccessRequest input) {
        // must differ other rule with access req type to avoid match conflict with other rules
        boolean val = false;
        if(input.getTypeOfRequest().equals("badge_evaluation")){
            val = true;
            if(!input.getUser().equals("non_assigned")){
                input.setEvaluation(true);
            }
        }
        return val;
    }

    @Override
    public Decision process(AccessRequest input) {
        Decision decision = null;
        if(input.isEvaluation()){
        decision = new Decision(input.getUser(),
                RequestState.PRE_EVALUATION);
        decision.setTypeOfRequestToDemand("role_evaluation");
        DecisionRepo.addDecision(decision);
        }else {
            decision = new Decision(RequestState.REJECTED,
                    "ACCESS DENIED BY LEVEL MODEL: Badge Based Access Control ... " +
                            "You may contact your administrator for more information");
        }
        return decision;
    }
}
