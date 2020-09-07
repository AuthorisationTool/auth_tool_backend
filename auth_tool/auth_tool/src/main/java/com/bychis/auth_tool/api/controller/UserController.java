package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.model.dbEntities.User;
import com.bychis.auth_tool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        this.userService.createUser(user);
    }

    @DeleteMapping("/{userID}")
    public void deleteUser(@PathVariable Long userID){
        this.userService.deleteUser(userID);
    }

    @GetMapping("/{userID}")
    public Optional<User> getUserByID(@PathVariable Long userID){
        return this.userService.getUserByID(userID);
    }

    @GetMapping
    public List<User> getUsersAll(){
        return this.userService.getUsersAll();
    }

    @PutMapping("/{userID}/role")
    public void updateUserRoleList(@PathVariable Long userID,@RequestBody String roleID){
        this.userService.updateUserRoles(userID,roleID);
    }

    @GetMapping("/{userID}/roles")
    public String getUserRole(@PathVariable Long userID){
        return this.userService.getUserRoles(userID);
    }
}
