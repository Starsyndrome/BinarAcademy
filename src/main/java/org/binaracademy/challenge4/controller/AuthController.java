package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.DTO.request.LoginRequest;
import org.binaracademy.challenge4.DTO.request.SignupRequest;
import org.binaracademy.challenge4.DTO.response.JwtResponse;
import org.binaracademy.challenge4.DTO.response.MessageResponse;
import org.binaracademy.challenge4.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest login) {
        return ResponseEntity.ok()
                .body(authService.authenticateUser(login));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok()
                .body(authService.registerUser(signupRequest));
    }
}