package com.example.vkr.service;

import com.example.vkr.model.User;
import com.example.vkr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    public void updateBonusesUser(Long userId, int bonuses){
        userRepository.updateBonus(bonuses, userId);
    }
}
