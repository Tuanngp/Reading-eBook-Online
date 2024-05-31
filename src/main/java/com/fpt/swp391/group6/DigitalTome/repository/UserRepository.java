package com.fpt.swp391.group6.DigitalTome.repository;

import com.fpt.swp391.group6.DigitalTome.dto.UserDto;
import com.fpt.swp391.group6.DigitalTome.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);

    AccountEntity findByToken(String token);

    AccountEntity findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u.password FROM AccountEntity u WHERE u.username = :username")
    String findPasswordByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE AccountEntity u SET u.password = :newPassword WHERE u.username = :username")
    void updatePasswordByUsername(@Param("username") String username, @Param("newPassword") String newPassword);

    @Query("SELECT new com.fpt.swp391.group6.DigitalTome.dto.UserDto(u.id, u.email, u.address, u.fullname, u.phone, u.avatarPath, u.description, u.point, u.isNotification) FROM AccountEntity u WHERE u.username = :username")
    UserDto findByUser(@Param("username") String username);


    @Query("SELECT u FROM AccountEntity u WHERE u.email = :email AND u.password = :password")
    AccountEntity findEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u.email FROM AccountEntity u WHERE u.isNotification = :isNotification")
    List<String> findAllGmailNotification(@Param("isNotification") Integer isNotification);

}

