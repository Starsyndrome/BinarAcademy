package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImplements implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean addNewUsers(Users users) {
        return Optional.ofNullable(users)
                .map(users1 -> userRepository.save(users))
                .map(result -> {
                    boolean isSuccess = Objects.nonNull(users);
                    if (isSuccess) {
                        log.info("Berhasil menambahkan user ke database dengan username: {}", users.getUsername());
                    }
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Gagal menambahkan user dan data tidak terinput ke database");
                    return Boolean.FALSE;
                });
    }
}