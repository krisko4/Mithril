package com.website.demo.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository = UserRepository.getInstance();


    public User post(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(), 4);
        return userRepository.post(user);
    }

    public List<User> getUsers() {
        return userRepository.userList;
    }
}
