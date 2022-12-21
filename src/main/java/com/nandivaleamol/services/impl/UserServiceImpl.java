package com.nandivaleamol.services.impl;

import com.nandivaleamol.entities.User;
import com.nandivaleamol.exceptions.ResourceNotFoundException;
import com.nandivaleamol.repositories.UserRepository;
import com.nandivaleamol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createNewUser(User user) {
        user.setRow_creation_timestamp(new Date());
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User updateUserById(User user, Integer userId) {
        var userUpdate = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userUpdate.setName(user.getName().strip());  // .strip() - for remove beginning and ending white spaces
        userUpdate.setAddress(user.getAddress().strip());
        userUpdate.setState(user.getState().strip());
        userUpdate.setPincode(user.getPincode().strip());
        return this.userRepository.save(userUpdate);
    }

    @Override
    public void deleteUserById(Integer userId) {
        var user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "Id", userId));
        this.userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
