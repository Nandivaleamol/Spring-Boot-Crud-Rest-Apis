package com.nandivaleamol.services;

import com.nandivaleamol.entities.User;

import java.util.List;

public interface UserService {

    User createNewUser(User user);

    User getUserById(Integer userId);

    User updateUserById(User user, Integer userId);

    void deleteUserById(Integer userId);

    List<User> getAllUsers();

}
