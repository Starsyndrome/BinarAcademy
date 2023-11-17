package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.UserResponse;
import org.binaracademy.challenge4.DTO.responseController.UserEmailUpdate;
import org.binaracademy.challenge4.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void updateUsername_Test(){
        UserResponse users = UserResponse.builder()
                .username("User Test")
                .emailAddress("usertest@gmail.com")
                .build();

//        Mockito.when(userService.updateUserUsername(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        ResponseEntity<String> updateUsername = userController
                .updateUsername("User Controller Test", "User Test", users);
        Mockito.verify(userService, Mockito.times(1))
                .updateUserUsername(Mockito.anyString(), Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, updateUsername.getStatusCode());
        Assertions.assertEquals("Update username successfully, new username: User Controller Test"
                ,updateUsername.getBody());
    }

    @Test
    void updateEmail_Test(){
        UserResponse userResponse = UserResponse.builder()
                .username("User Controller Test")
                .emailAddress("usertest@gmail.com")
                .build();

//        Mockito.when(userService.updateUserEmail(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        ResponseEntity<UserEmailUpdate> updateEmail = userController
                .updateEmail("usercontrollertest@gmail.com", "User Controller Test", userResponse);
        Mockito.verify(userService, Mockito.times(1))
                .updateUserEmail(Mockito.anyString(), Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, updateEmail.getStatusCode());
        Assertions.assertEquals("usercontrollertest@gmail.com",
                Objects.requireNonNull(updateEmail.getBody()).getEmail());
    }

    @Test
    void deleteUser_Test(){
        UserResponse userResponse = UserResponse.builder()
                .username("User Controller Test")
                .build();

        ResponseEntity<String> deleteUserFromUsername = userController.deleteUserFromUsername("User Controller Test");
        Mockito.verify(userService, Mockito.times(1)).deleteUserFromUsername(Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, deleteUserFromUsername.getStatusCode());
        Assertions.assertEquals("User with username: User Controller Test successfully deleted!",
                deleteUserFromUsername.getBody());
    }
}