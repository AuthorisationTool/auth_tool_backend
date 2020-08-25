package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/policy")
@RestController
@CrossOrigin("*")
public class PolicyController {
    private PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService){
        this.policyService = policyService;
    }
    @PostMapping
    public void initiatePolicy(){
    policyService.initiatePolicy();
    }
}
