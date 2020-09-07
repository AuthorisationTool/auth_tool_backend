package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.dbEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository("User")
public interface UserRepo extends JpaRepository<User, Long> {
    default User getUserByStringId(String id){
        List<User> users = this.findAll();
        User user = new User();
        loop: for(User tmp : users){
            if(tmp.getUserID().toString().equals(id)){
                user = tmp;
            }

        }
        return user;
    }
}
