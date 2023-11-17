package org.binaracademy.challenge4.service;


public interface UserService {

    void updateUserUsername(String newUsername, String oldUsername);

    void updateUserEmail(String newEmail, String username);

    void deleteUserFromUsername(String username);
}