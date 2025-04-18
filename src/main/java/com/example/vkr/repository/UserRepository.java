package com.example.vkr.repository;

import com.example.vkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    List<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE phone = :phone", nativeQuery = true)
    List<User> findByPhone(@Param("phone") String phone);

    @Modifying
    @Query(value = "UPDATE users SET bonuses = :bonuses WHERE id = :id", nativeQuery = true)
    void updateBonus(@Param("bonuses") int bonuses, @Param("id") Long id);
}
