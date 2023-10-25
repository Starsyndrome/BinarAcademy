package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;

public interface UserService {
    void addNewUsers(Users users); // Auto generate id
//    void updateUserUsername(String oldUsername, String newUsername, String Id);
    void updateUserUsername(String newUsername, String oldUsername);
//    void updateUserEmail(String oldEmail, String newEmail, String Id);
    void updateUserEmail(String newEmail, String oldEmail);
//    void updateUserPassword(String oldPassword, String newPassword, String Id);
    void updateUserPassword(String newPassword, String username);
    void deleteUserFromID(Users users);
    void deleteUserFromUsername(String username);
}