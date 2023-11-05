package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.repository.UserRepository;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean updateUserUsername(String newUsername, String oldUsername){
        try {
            userRepository.editUsersFromUsername(newUsername, oldUsername);
            return true;
        } catch (Exception e){
            log.error("Error");
            return false;
        }
    }

    @Override
    public Boolean updateUserEmail(String newEmail, String username) {
        try{
            userRepository.editUsersFromEmail(newEmail, username);
            return true;
        } catch(Exception e){
            log.error("Error");
            return false;
        }
    }

    @Override
    public void deleteUserFromUsername(String username) {
        try {
            userRepository.deleteUserFromUsername(username);
            log.info("Successfully deleted user!");
        } catch (Exception e){
            log.error("Error");
        }
    }
}