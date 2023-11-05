package org.binaracademy.challenge4.service.mock;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UserRepository;
import org.binaracademy.challenge4.service.implement.UserServiceImplements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @InjectMocks
    UserServiceImplements userService;

    @Mock
    UserRepository userRepository;

    @Test
    void deleteUser_Test(){
        Users users = Users.builder()
                .id(3L)
                .email("alpha@gmail.com")
                .build();

        Mockito.when(userRepository.findByUsername("Alpha")).thenReturn(Optional.ofNullable(users));
        Assertions.assertAll(() -> userService.deleteUserFromUsername("Alpha"));
    }

    @Test
    void updateUsername_Test() {
        Mockito.when(userRepository.findByUsername("User Test")).thenReturn(Optional.ofNullable(Users.builder()
                        .username("User Test")
                .build()));

        Boolean updateUsername = userService.updateUserUsername("User Test Mock", "User Test");
        Mockito.verify(userRepository, Mockito.times(1)).editUsersFromUsername(Mockito.anyString(), Mockito.anyString());

        Assertions.assertTrue(updateUsername);
    }

    @Test
    void updateEmail_Test() {
        Mockito.when(userRepository.findByUsername("User Test")).thenReturn(Optional.ofNullable(Users.builder()
                        .username("User Test")
                        .email("usertest@gmail.com")
                .build()));

        Boolean updateEmail = userService.updateUserEmail("usertestmock@gmail.com", "User Test");
        Mockito.verify(userRepository, Mockito.times(1)).editUsersFromEmail(Mockito.anyString(), Mockito.anyString());

        Assertions.assertTrue(updateEmail);
    }
}