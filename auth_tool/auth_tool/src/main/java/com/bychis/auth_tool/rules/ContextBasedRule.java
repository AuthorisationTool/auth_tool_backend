package com.bychis.auth_tool.rules;

import com.bychis.auth_tool.model.ContextBasedModel.constraint_types.EnvironmentConstraintType;

import java.util.List;

public class ContextBasedRule implements RuleI<AccessRequest,Decision>{
    private final String roleID;
    private final List<EnvironmentConstraintType> constraints;
    public ContextBasedRule(String roleID, List<EnvironmentConstraintType> constraints) {
        this.roleID = roleID;
        this.constraints = constraints; }
    @Override
    public boolean matches(AccessRequest input) {
        input.setEvaluation(false);
        if(input.getTypeOfRequest().equals("environment_evaluation") &&
                input.getRoleID().equals(roleID)
        ){
            List<Value> values = input.getValueList();
            loop1: for(Value value: values){
                loop2: for(EnvironmentConstraintType constraint: constraints){
                    if(value.getType().equals(constraint.getEnvironmentConstraintTypeName())){
                    input.setEvaluation(constraint.evaluateValue(value.getArg()));
                    if(!input.isEvaluation()) break loop1;
                    } } } } return true; }
    @Override
    public Decision process(AccessRequest input) {
        Decision decision = null;
        if(input.isEvaluation()){
            decision = DecisionRepo.getDecisionByID(input.getRequestID());
            decision.setRequestState(RequestState.GRANTED);
            decision.grantAccess();
            DecisionRepo.updateDecision(input.getRequestID(),decision);
        } else{ decision = new Decision(RequestState.REJECTED, "ACCESS DENIED");
        } return decision; }}

