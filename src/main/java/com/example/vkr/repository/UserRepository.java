package com.example.vkr.repository;

import com.example.vkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE phone = :phone", nativeQuery = true)
    User findByPhone(@Param("phone") String phone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET bonuses = :bonuses WHERE id = :id", nativeQuery = true)
    void updateBonus(@Param("bonuses") int bonuses, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (full_name, email, password_hash, phone, bonuses) " +
            "VALUES (:fullName, :email, :passwordHash, :phoneNumber, :bonuses)", nativeQuery = true)
    void addUser(@Param("fullName") String fullName, @Param("email") String email,
                 @Param("passwordHash") String passwordHash, @Param("phoneNumber") String phoneNumber,
                 @Param("bonuses") int bonuses);

}
