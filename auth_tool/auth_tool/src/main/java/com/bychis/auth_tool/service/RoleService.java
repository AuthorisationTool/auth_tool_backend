package com.bychis.auth_tool.service;

import com.bychis.auth_tool.dao.RoleDao;
import com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(@Qualifier("Role") RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void createRole(Role role){
        roleDao.createRole(role);
    }

    public Role getRole(String id){
        return roleDao.getRole(id);
    }

    public List<Role> getRoles(){
        return roleDao.getRoles();
    }

    public void updateRole(String id, Role role){
        this.roleDao.updateRole(id,role);
    }

    public void deleteRole(String id){
        roleDao.deleteRole(id);
    }

    public int getLevelsCount(String id){
        return roleDao.getLevelsCount(id);
    }


}
