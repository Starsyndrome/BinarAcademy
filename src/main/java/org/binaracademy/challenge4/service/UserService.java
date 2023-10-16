package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;

public interface UserService {
    Boolean addNewUsers(Users users); // Auto generate id
    Boolean submitNewUser(Users users); // id bisa diisi sendiri
    void updateUserUsername(String oldUsername, String newUsername, String Id);
    void updateUserEmail(String oldEmail, String newEmail, String Id);
    void updateUserPassword(String oldPassword, String newPassword, String Id);
    void deleteUserFromID(Users users);
}