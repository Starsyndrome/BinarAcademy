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
                        .username("Alfarizki")
                        .emailAddress("alfarizki09@gmail.com")
                        .password("112233")
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
                        .userID("ec4113e0-56d5-48dc-9402-df5ec5c2e4ac")
                .build());
    }

    @Test
    void updateUserPassword_Test(){
        userService.updateUserPassword("112233", "299904",
                "7d912a1d-b974-4526-8a1b-0bd959585922");
    }

    @Test
    void updateUserUsername_Test() {
        userService.updateUserUsername("Alfarizki", "Starsyndrome",
                "7d912a1d-b974-4526-8a1b-0bd959585922");
    }

    @Test
    void updateUserEmail_Test(){
        userService.updateUserEmail("alfarizki09@gmail.com", "alfarizki@gmail.com",
                "7d912a1d-b974-4526-8a1b-0bd959585922");
    }
}