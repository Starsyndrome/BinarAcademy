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
    void addNewUser_Test(){ // id auto generate
        userService.addNewUsers(Users.builder()
                        .username("User Test")
                        .emailAddress("usertest99@gmail.com")
                        .password("testingtesting")
                .build());
    }

    @Test
    void submitNewUser_Test(){ // id bisa diisi sendiri
        userService.submitNewUser(Users.builder()
                        .userID("userid123")
                        .emailAddress("testsubmit@gmail.com")
                        .username("Test Submit New User")
                        .password("testtesttest")
                .build());
    }

    @Test
    void deleteUser_Test() {
        userService.deleteUserFromID(Users.builder()
                        .userID("f645193e-1ac0-401c-92d1-f422f4aaccbb")
                .build());
    }

    @Test
    void updateUserPassword_Test(){
        userService.updateUserPassword("testingtesting", "testing2x",
                "f645193e-1ac0-401c-92d1-f422f4aaccbb");
    }

    @Test
    void updateUserUsername_Test() {
        userService.updateUserUsername("User Test", "User Test Success",
                "f645193e-1ac0-401c-92d1-f422f4aaccbb");
    }

    @Test
    void updateUserEmail_Test(){
        userService.updateUserEmail("usertest123@gmail.com", "usertest@gmail.com",
                "ec4113e0-56d5-48dc-9402-df5ec5c2e4ac");
    }
}