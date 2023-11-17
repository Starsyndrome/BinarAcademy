package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.DTO.response.UserResponse;
import org.binaracademy.challenge4.DTO.responseController.UserEmailUpdate;
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

    @PutMapping(value = "/update/username/{username}")
    public ResponseEntity<String> updateUsername(@RequestParam("newUsername") String newUserUsername,
                                 @PathVariable("username") String oldUserUsername,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserUsername(newUserUsername, oldUserUsername);
        return ResponseEntity.ok()
                .body("Update username successfully, new username: " + newUserUsername);
    }

    @PutMapping(value = "/update/email/{username}")
    public ResponseEntity<UserEmailUpdate> updateEmail(@RequestParam("newEmail") String newEmail,
                                 @PathVariable("username") String username,
                                 @RequestBody UserResponse userResponse){
        userService.updateUserEmail(newEmail, username);
        return ResponseEntity.ok()
                .body(UserEmailUpdate.builder()
                        .username(username)
                        .email(newEmail)
                        .info("Update user email successfully!")
                        .build());
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<String> deleteUserFromUsername(@PathVariable("username") String username){
        userService.deleteUserFromUsername(username);
        return ResponseEntity.ok()
                .body("User with username: " + username + " successfully deleted!");
    }
}