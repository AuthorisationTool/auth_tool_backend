package com.bychis.auth_tool.rules;

import com.bychis.auth_tool.dao.BluetoothBadgeRepo;
import com.bychis.auth_tool.model.dbEntities.BluetoothBadge;
import com.bychis.auth_tool.service.BluetoothBadgeService;
import com.bychis.auth_tool.service.ConstraintService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class RuleEngineFactory {

    public static RuleEngine ruleEngine = new RuleEngine();


    public RuleEngineFactory()
    {
        System.out.println("ruleenginecreato success");
    }



    public void createRuleEngine( List<String> roleIDList) {

        ruleEngine.registerRule(new BadgeBasedRule());

        for (String roleID : roleIDList) {
            ruleEngine.registerRule(new RoleBasedRule(roleID));
            ruleEngine.registerRule(new ProfileBasedRule(roleID,
                    ConstraintService.getProfileConstraints(roleID,
                            1)));
            ruleEngine.registerRule(new ContextBasedRule(roleID,
                    ConstraintService.getEnvironmentConstraints(roleID,2)));
        }
    }
}