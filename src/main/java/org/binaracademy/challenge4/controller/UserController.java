package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserController {
    @Autowired
    UserService userService;
    private Scanner scanner = new Scanner(System.in);

    public void addUser(){
        System.out.print("Email: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Username: ");
        String addUsername = scanner.nextLine();
        System.out.print("Password: ");
        String addPassword = scanner.nextLine();

        Users users = Users.builder()
                .emailAddress(emailAddress)
                .username(addUsername)
                .password(addPassword)
                .build();
        userService.addNewUsers(users);
    }
}
