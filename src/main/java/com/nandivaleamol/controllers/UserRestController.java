package com.nandivaleamol.controllers;

import com.nandivaleamol.entities.User;
import com.nandivaleamol.payloads.ApiResponse;
import com.nandivaleamol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

//    Insert new record in database
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createNewUser(user));
    }

//    Get the record based on given id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable int userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

//    Update the existing record
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int userId){
        return ResponseEntity.ok(this.userService.updateUserById(user,userId));
    }

//    Delete the record based on give id
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
        this.userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted successfully!!",true));
    }

//    Get all records
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}