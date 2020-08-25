package com.bychis.auth_tool.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DecisionRepo {
    private static List<Decision> decisionlist;

    @Autowired
    public DecisionRepo(){
        decisionlist = new ArrayList<>();
    }

    public static void addDecision(Decision decision){
        DecisionRepo.decisionlist.add(decision);
    }

    public static void updateDecision(String id, Decision decisionUpdate){
        loop: for(Decision decision : DecisionRepo.decisionlist){
            if(decision.getId().equals(id)){
                decisionUpdate.setId(id);
                decisionlist.set(decisionlist.indexOf(decision),decisionUpdate);
                break loop;
            }
        }
    }

    public static Decision getDecisionByID(String id){
        Decision decision = null;
        loop: for (Decision tmp: decisionlist){
            if(tmp.getId().equals(id)){
                decision = tmp;
                break loop;
            }
        }
        return decision;
    }


    public static void removeDecision(String id){
        loop: for(Decision decision : DecisionRepo.decisionlist){
            if(decision.getId().equals(id)){
                DecisionRepo.decisionlist.remove(decision);
                decision = null;
                break loop;
            }
        }
    }



}
