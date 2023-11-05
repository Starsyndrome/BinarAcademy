package org.binaracademy.challenge4.service;


public interface UserService {

    Boolean updateUserUsername(String newUsername, String oldUsername);

    Boolean updateUserEmail(String newEmail, String username);

    void deleteUserFromUsername(String username);
}