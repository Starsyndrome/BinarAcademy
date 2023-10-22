package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addNewUsers(Users users) {
        log.info("Process add new user");
        Optional.ofNullable(users)
                .map(users1 -> userRepository.save(users))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully added user with username: {}", users.getUsername());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Failed to add new user");
                    return Boolean.FALSE;
                });
        log.info("Successfully add new user!");
    }

    @Override
    public void updateUserUsername(String newUsername, String oldUsername){
        try {
            userRepository.editUsersFromUsername(newUsername, oldUsername);
        } catch (Exception e){
            log.error("Error");
        }
    }

    @Override
    public void updateUserEmail(String newEmail, String oldEmail) {
        try{
            userRepository.editUsersFromEmail(newEmail, oldEmail);
        } catch(Exception e){
            log.error("Error");
        }
    }

    @Override
    public void updateUserPassword(String newPassword, String oldPassword) {
        try {
            userRepository.editUsersFromPassword(newPassword, oldPassword);
        } catch (Exception e) {
            log.error("Error");
        }
    }

    @Override
    public void deleteUserFromID(Users users) {
        try {
            userRepository.deleteById(users.getUserID());
            log.info("Successfully deleted user by ID: {}", users.getUserID());
        } catch (Exception e) {
            log.error("Error");
        }
    }

    @Override
    public void deleteUserFromUsername(String username) {
        try {
            userRepository.deleteProductFromName(username);
            log.info("Successfully deleted user!");
        } catch (Exception e){
            log.error("Error");
        }
    }
}