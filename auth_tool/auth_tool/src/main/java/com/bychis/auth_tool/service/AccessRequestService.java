package com.bychis.auth_tool.service;

import com.bychis.auth_tool.dao.BluetoothBadgeRepo;
import com.bychis.auth_tool.dao.UserRepo;
import com.bychis.auth_tool.model.dbEntities.User;
import com.bychis.auth_tool.rules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessRequestService {

    private final DecisionRepo decisionRepo;
    private final BluetoothBadgeRepo bluetoothBadgeRepo;
    private final UserRepo userRepo;
    private final ConstraintService constraintService;

    @Autowired
    public AccessRequestService(DecisionRepo decisionRepo,
                                BluetoothBadgeRepo bluetoothBadgeRepo,
                                UserRepo userRepo,
                                ConstraintService constraintService) {
        this.decisionRepo = decisionRepo;
        this.bluetoothBadgeRepo = bluetoothBadgeRepo;
        this.userRepo = userRepo;
        this.constraintService = constraintService;
    }

    public Decision readBadgeEvaluationDecision(AccessRequest accessRequest){
        String holder_uid = bluetoothBadgeRepo
                .getHolderUidByBid(accessRequest.getBid());
        // Evaluation is false because the request is not yet evaluated
        accessRequest.setEvaluation(false);
        accessRequest.setUser(holder_uid);
        Decision decision = RuleEngineFactory.ruleEngine.rule(accessRequest);
        return decision;
    }

    public Decision readRoleEvaluationDecision(AccessRequest accessRequest){
        // Evaluation is false because the request is not yet evaluated
        accessRequest.setEvaluation(false);
        User user = userRepo.getUserByStringId(accessRequest.getUser());
        accessRequest.setRoleID(user.getMemberOf());
        Decision decision = RuleEngineFactory.ruleEngine.rule(accessRequest);
        if(decision.getRequestState().equals(RequestState.ON_EVALUATION)){
        decision.setDemanded_values(constraintService.
                getTypeOfConstraintToDemand
                        (accessRequest.getRoleID(),
                1));}
        return decision;
    }

    public Decision readProfileEvaluationDecision(AccessRequest accessRequest){
        accessRequest.setEvaluation(false);
        Decision decision = RuleEngineFactory.ruleEngine.rule(accessRequest);
        if(decision.getRequestState().equals(RequestState.ON_EVALUATION)){
            decision.setDemanded_values(constraintService
                    .getTypeOfConstraintToDemand(decision.getRoleID(),2));
        }
        return decision;
    }

    public Decision readContextEvaluationDecision(AccessRequest accessRequest){
        accessRequest.setEvaluation(false);
        Decision decision = RuleEngineFactory.ruleEngine.rule(accessRequest);
        if(decision.getRequestState().equals(RequestState.ON_EVALUATION)){
            // giving access to remote IP address
        }
        return decision;
    }
    
}
