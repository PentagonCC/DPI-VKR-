package com.example.vkr.service;

import com.example.vkr.model.User;
import com.example.vkr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public void updateBonusesUser(Long userId, int bonuses) {
        userRepository.updateBonus(bonuses, userId);
    }

    public void addUser(String fullName, String email, String passwordHash, String phoneNumber, int bonuses) {
        userRepository.addUser(fullName, email, passwordHash, phoneNumber, bonuses);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с такой почтой не найден " + email);
        }
        return user;
    }
}
