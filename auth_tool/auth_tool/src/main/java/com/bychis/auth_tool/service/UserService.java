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

    public void deleteUser(Long userID){
        this.userRepo.deleteById(userID);
    }

    public List<User> getUsersAll(){
        return this.userRepo.findAll();
    }

    public Optional<User> getUserByID(Long userID){
        return this.userRepo.findById(userID);
    }

    public void updateUserRoles(Long userID, String roleID){
        User user = this.userRepo.getOne(userID);
        user.setMemberOf(roleID);
        this.userRepo.save(user);
    }

    public String getUserRoles(Long userID){
        return this.userRepo.getOne(userID).getMemberOf();
    }
}
