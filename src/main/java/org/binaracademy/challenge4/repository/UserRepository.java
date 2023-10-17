package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query(nativeQuery = true, value = "insert into users(userID, username, email_address, password) " +
    "values(:userID, :username, :email_address, :password)")
    void submitNewUser(@Param("userID") String userID, @Param("username") String username,
                          @Param("email_address") String emailAddress, @Param("password") String password);

    @Query(nativeQuery = true, value = "update users set username = :newUsername where username = :oldUsername")
    void editUsersFromUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

    @Query(nativeQuery = true, value = "update users set email_address = :newEmail where email_address = :oldEmail")
    void editUsersFromEmail(@Param("oldEmail") String oldEmail, @Param("newEmail") String newEmail);

    @Query(nativeQuery = true, value = "update users set password = :newPassword where password = :oldPassword")
    void editUsersFromPassword(@Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
}