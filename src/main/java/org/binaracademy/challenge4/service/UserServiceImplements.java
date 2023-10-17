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
    public Boolean addNewUsers(Users users) {
        return Optional.ofNullable(users)
                .map(users1 -> userRepository.save(users))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Berhasil menambahkan user ke database dengan username: {}", users.getUsername());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Gagal menambahkan user dan data tidak terinput ke database");
                    return Boolean.FALSE;
                });
    }

    @Override
    public Boolean submitNewUser(Users users) {
        try {
            userRepository.submitNewUser(users.getUserID(), users.getUsername(),
                    users.getEmailAddress(), users.getPassword());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void updateUserUsername(String oldUsername, String newUsername, String Id){
        try {
            if (userRepository.existsById(Id)) {
                userRepository.editUsersFromUsername(oldUsername, newUsername);
            }
        } catch (Exception e){
            log.error("Error");
        }
    }

    @Override
    public void updateUserEmail(String oldEmail, String newEmail, String Id) {
        try{
            if (userRepository.existsById(Id)){
                userRepository.editUsersFromEmail(oldEmail, newEmail);
            }
        } catch(Exception e){
            log.error("Error");
        }
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword, String Id) {
        try {
            if (userRepository.existsById(Id)){
                userRepository.editUsersFromPassword(oldPassword, newPassword);
            }
        } catch (Exception e) {
            log.error("Error");
        }
    }

    @Override
    public void deleteUserFromID(Users users) {
        try {
            if (userRepository.existsById(users.getUserID())) {
                userRepository.deleteById(users.getUserID());
                log.info("Successfully deleted user by ID: {}", users.getUserID());
            }
        } catch (Exception e) {
            log.error("Error");
        }
    }
}