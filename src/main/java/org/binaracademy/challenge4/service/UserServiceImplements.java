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
    public Boolean updateUserFromUsername(String oldUsername, String newUsername) {
        try {
            userRepository.editUsersFromUsername(oldUsername, newUsername);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean updateUserFromEmail(String oldEmail, String newEmail) {
        try {
            userRepository.editUsersFromEmail(oldEmail, newEmail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean updateUserFromPassword(String oldPassword, String newPassword) {
        try {
            userRepository.editUsersFromPassword(oldPassword, newPassword);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteUserFromUsername(String userUsername) {
        try {
            userRepository.deleteUsersFromUsername(userUsername);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}