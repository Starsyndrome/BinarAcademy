package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.DTO.request.LoginRequest;
import org.binaracademy.challenge4.DTO.request.SignupRequest;
import org.binaracademy.challenge4.DTO.response.JwtResponse;
import org.binaracademy.challenge4.DTO.response.MessageResponse;

public interface AuthService {

    MessageResponse registerUser(SignupRequest signupRequest);

    JwtResponse authenticateUser(LoginRequest login);
}