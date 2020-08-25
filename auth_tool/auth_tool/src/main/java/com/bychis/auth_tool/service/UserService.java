package com.bychis.auth_tool.service;

import com.bychis.auth_tool.dao.UserRepo;
import com.bychis.auth_tool.model.dbEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void createUser(User user){
        this.userRepo.save(user);
    }

    public void deleteUser(String userID){
        this.userRepo.deleteById(UUID.fromString(userID));
    }

    public List<User> getUsersAll(){
        return this.userRepo.findAll();
    }

    public Optional<User> getUserByID(String userID){
        return this.userRepo.findById(UUID.fromString(userID));
    }

    public void updateUserRoles(String userID, String roleID){
        User user = this.userRepo.getOne(UUID.fromString(userID));
        user.setMemberOf(roleID);
        this.userRepo.save(user);
    }

    public String getUserRoles(String userID){
        return this.getUserByID(userID).get().getMemberOf();
    }
}
