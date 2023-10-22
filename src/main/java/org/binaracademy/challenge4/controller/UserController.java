package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.model.response.UserResponse;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/addUser", consumes = "application/json")
    public String addNewUser(@RequestBody Users users){
        userService.addNewUsers(users);
        return "Add new user with username: " + users.getUsername() + " successfully!";
    }

    @PutMapping(value = "/updateUsername/{username}")
    public String updateUsername(@RequestParam("newUsername") String newUserUsername,
                                 @PathVariable("username") String oldUserUsername,
                                 @RequestBody UserResponse userResponse){;
        userService.updateUserUsername(newUserUsername, oldUserUsername);
        return "Update username successfully, new username: " + newUserUsername;
    }

    @PutMapping(value = "/updateEmail/{email}")
    public String updateEmail(@RequestParam("newEmail") String newEmail,
                                 @PathVariable("email") String oldEmail,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserEmail(newEmail, oldEmail);
        return "Update email successfully, new email: " + newEmail;
    }

    @PutMapping(value = "/updatePassword/{password}")
    public String updatePassword(@RequestParam("newPassword") String newPassword,
                                 @PathVariable("password") String oldPassword,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserPassword(newPassword, oldPassword);
        return "Update password successfully!";
    }

    @DeleteMapping(value = "/deleteUser/{username}")
    public String deleteUserFromID(@PathVariable("username") String username){
        userService.deleteUserFromUsername(username);
        return "User with username: " + username + " successfully deleted";
    }
}