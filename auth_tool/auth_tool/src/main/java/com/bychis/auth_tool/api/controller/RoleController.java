package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel.Role;
import com.bychis.auth_tool.api.request_holders.RoleRequestHolder;
import com.bychis.auth_tool.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("policy/role")
@RestController
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public void createRole(@RequestBody RoleRequestHolder roleRequestHolder){
        Role role = new Role(roleRequestHolder.getRoleName(),
                roleRequestHolder.getClassification(),
                roleRequestHolder.getClassificationArg());
        this.roleService.createRole(role);
    }

    @GetMapping(path = "/{roleID}")
    public Role getRole(@PathVariable String roleID){
        return this.roleService.getRole(roleID);
    }

    @GetMapping
    public List<Role> getRoles(){
        return this.roleService.getRoles();
    }

    @GetMapping(path = "/{roleID}/count")
    public int getLevelsCount(@PathVariable String roleID){
        return this.roleService.getLevelsCount(roleID);
    }

    @DeleteMapping(path = "/{roleID}")
    public void deleteRole(@PathVariable String roleID){
        this.roleService.deleteRole(roleID);
    }

    @PutMapping(path = "/{roleID}")
    public void updateRole(@PathVariable String roleID,@RequestBody RoleRequestHolder roleRequestHolder){
        Role role = new Role(roleRequestHolder.getRoleName(),
                roleRequestHolder.getClassification(),
                roleRequestHolder.getClassificationArg());
        this.roleService.updateRole(roleID,role);
    }
}
