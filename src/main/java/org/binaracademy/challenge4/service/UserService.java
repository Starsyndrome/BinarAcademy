package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;

public interface UserService {
    Boolean addNewUsers(Users users);
    Boolean submitNewUser(Users users);
    Boolean updateUserFromUsername(String oldUsername, String newUsername);
    Boolean updateUserFromEmail(String oldEmail, String newEmail);
    Boolean updateUserFromPassword(String oldPassword, String newPassword);
    Boolean deleteUserFromUsername(String userUsername);
}