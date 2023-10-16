package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void addNewUser(){
        userService.addNewUsers(Users.builder()
                        .username("Test123")
                        .emailAddress("test123@gmail.com")
                        .password("testtest123")
                .build());
    }

    @Test
    void deleteUser() {
        userService.deleteUserFromID(Users.builder()
                        .userID("001UID")
                .build());
    }

    @Test
    void updateUserPassword(){
        userService.updateUserPassword("testtest123", "testing123",
                "ec4113e0-56d5-48dc-9402-df5ec5c2e4ac");
    }

    @Test
    void updateUserUsername() {
        userService.updateUserUsername("Testing123", "Testing12345",
                "ec4113e0-56d5-48dc-9402-df5ec5c2e4ac");
    }

    @Test
    void updateUserEmail(){
        userService.updateUserEmail("test123@gmail.com", "testing1923@gmail.com",
                "ec4113e0-56d5-48dc-9402-df5ec5c2e4ac");
    }
}