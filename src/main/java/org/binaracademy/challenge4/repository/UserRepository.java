package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    @Query(nativeQuery = true, value = "delete from users where username = :username")
    void deleteProductFromName(@Param("username") String username);

    @Query(nativeQuery = true, value = "update users set username = :newUsername where username = :oldUsername")
    void editUsersFromUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

    @Query(nativeQuery = true, value = "update users set email_address = :newEmail where email_address = :oldEmail")
    void editUsersFromEmail(@Param("newEmail") String newEmail, @Param("oldEmail") String oldEmail);

    @Query(nativeQuery = true, value = "update users set password = :newPassword where password = :oldPassword")
    void editUsersFromPassword(@Param("newPassword") String newPassword, @Param("oldPassword") String oldPassword);
}