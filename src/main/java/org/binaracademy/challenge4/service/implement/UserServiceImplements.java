package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UserRepository;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Transactional
@Service
public class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Async
    @Override
    public void updateUserUsername(String newUsername, String oldUsername){
        try {
            Users users = userRepository.findByUsername(oldUsername).orElse(null);
            if (!Optional.ofNullable(users).isPresent()){
                log.info("Username is not available!");
            }
            userRepository.editUsersFromUsername(newUsername, oldUsername);
            log.info("Update success, new username: " + newUsername);
        } catch (Exception e){
            log.error("Updating username failed, please try again!");
        }
    }

    @Async
    @Override
    public void updateUserEmail(String newEmail, String username) {
        try{
            Users users = userRepository.findByUsername(username).orElse(null);
            if (!Optional.ofNullable(users).isPresent()){
                log.info("Username is not available!");
            }
            userRepository.editUsersFromEmail(newEmail, username);
            log.info("Update success, new email: " + newEmail);
        } catch(Exception e){
            log.error("Updating email failed, please try again!");
        }
    }

    @Async
    @Override
    public void deleteUserFromUsername(String username) {
        try { // Harus delete yang di table user_roles dulu baru bisa delete user
            Users users = userRepository.findByUsername(username).orElse(null);
            if (!Optional.ofNullable(users).isPresent()){
                log.info("User is not available");
            }
            userRepository.deleteUserFromUsername(username);
            log.info("Successfully deleted user!");
        } catch (Exception e){
            log.error("Deleting user failed, please try again!");
        }
    }
}