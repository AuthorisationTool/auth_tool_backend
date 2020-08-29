package com.bychis.auth_tool;

import com.bychis.auth_tool.dao.RoleDao;
import com.bychis.auth_tool.rules.RuleEngineFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AuthToolApplication {

	public static void main(String[] args) {
		RuleEngineFactory ruleEngineFactory = new RuleEngineFactory();
		File file = new File("Policy.xml");
		if(file.exists()){
		ruleEngineFactory.createRuleEngine(RoleDao.getRoleIDs());}
		SpringApplication.run(AuthToolApplication.class, args);

	}

}
