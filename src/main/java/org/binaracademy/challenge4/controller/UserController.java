package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.model.response.UserResponse;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/addUser", consumes = "application/json")
    public ResponseEntity addNewUser(@RequestBody Users users){
        userService.addNewUsers(users);
        return ResponseEntity.ok("Add new user with username: " +
                users.getUsername() + " successfully!");
    }

    @PutMapping(value = "/updateUsername/{username}")
    public ResponseEntity updateUsername(@RequestParam("newUsername") String newUserUsername,
                                 @PathVariable("username") String oldUserUsername,
                                 @RequestBody UserResponse userResponse){;
        userService.updateUserUsername(newUserUsername, oldUserUsername);
        return ResponseEntity.ok("Update username successfully, new username: " + newUserUsername);
    }

    @PutMapping(value = "/updateEmail/{email}")
    public ResponseEntity updateEmail(@RequestParam("newEmail") String newEmail,
                                 @PathVariable("email") String oldEmail,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserEmail(newEmail, oldEmail);
        return ResponseEntity.ok("Update email successfully, new email: " + newEmail);
    }

    @PutMapping(value = "/updatePassword/{username}")
    public ResponseEntity updatePassword(@RequestParam("newPassword") String newPassword,
                                 @PathVariable("username") String username,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserPassword(newPassword, username);
        return ResponseEntity.ok("Update password with username " + username + " successfully!");
    }

    @DeleteMapping(value = "/deleteUser/{username}")
    public ResponseEntity deleteUserFromID(@PathVariable("username") String username){
        userService.deleteUserFromUsername(username);
        return ResponseEntity.ok("User with username: " + username + " successfully deleted");
    }
}