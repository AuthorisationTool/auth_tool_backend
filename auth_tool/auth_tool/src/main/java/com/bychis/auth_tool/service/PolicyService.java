package com.bychis.auth_tool.service;
import com.bychis.auth_tool.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class PolicyService {
    private final PolicyDao policyDao;
    @Autowired
    public PolicyService(@Qualifier("Policy") PolicyDao policyDao){
        this.policyDao = policyDao;
    }
    public void initiatePolicy(){
        this.policyDao.initiatePolicyDao();
    }
}
