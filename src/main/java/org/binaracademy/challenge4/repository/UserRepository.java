package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void deleteById(Long id);

    Users getByUsername(String username);

    @Query(nativeQuery = true, value = "delete from users where username = :username")
    void deleteUserFromUsername(@Param("username") String username);

    @Query(nativeQuery = true, value = "update users set username = :newUsername where username = :oldUsername")
    void editUsersFromUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

    @Query(nativeQuery = true, value = "update users set email_address = :newEmail where username = :username")
    void editUsersFromEmail(@Param("newEmail") String newEmail, @Param("username") String username);
}