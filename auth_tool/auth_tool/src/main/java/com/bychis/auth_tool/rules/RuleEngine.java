package com.bychis.auth_tool.rules;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RuleEngine {


        List<RuleI<AccessRequest, Decision>> rules;


        public RuleEngine() {
            rules = new ArrayList<>();
            System.out.println("RuleEngine created successfully");
        }


        public Decision rule(AccessRequest accessRequest) {
            return rules.stream()
                    .filter(rule -> rule.matches(accessRequest))
                    .map(rule -> rule.process(accessRequest))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No Matching rule found"));
        }


        public RuleEngine registerRule(RuleI<AccessRequest, Decision> rule) {
            rules.add(rule);
            return this;
        }


    }
