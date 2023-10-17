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
                        .username("User Test")
                        .emailAddress("usertest99@gmail.com")
                        .password("testingtesting")
                .build());
    }

    @Test
    void deleteUser() {
        userService.deleteUserFromID(Users.builder()
                        .userID("f645193e-1ac0-401c-92d1-f422f4aaccbb")
                .build());
    }

    @Test
    void updateUserPassword(){
        userService.updateUserPassword("testingtesting", "testing2x",
                "f645193e-1ac0-401c-92d1-f422f4aaccbb");
    }

    @Test
    void updateUserUsername() {
        userService.updateUserUsername("User Test", "User Test Success",
                "f645193e-1ac0-401c-92d1-f422f4aaccbb");
    }

    @Test
    void updateUserEmail(){
        userService.updateUserEmail("usertest99@gmail.com", "usertest@gmail.com",
                "f645193e-1ac0-401c-92d1-f422f4aaccbb");
    }
}