package com.website.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class UserRepository {

    public List<User> userList = new ArrayList<>();

    private static UserRepository instance;

    private UserRepository(){
    }

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public User post(User user){
        userList.add(user);
        return user;
    }


}
