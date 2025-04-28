package com.example.vkr.controller;

import com.example.vkr.model.User;
import com.example.vkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Ошибка валидации: " + error.getDefaultMessage());
            });
            return "registration";
        }
        if(userService.getUserByPhone(user.getPhoneNumber()) != null ||
                userService.getByEmail(user.getEmail()) != null){
            model.addAttribute("mainFieldError",
                    "Пользователь с таким email или номером уже существует");
            return "registration";
        }

        user.setFullName(user.getName() + " " + user.getSurname());
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setBonuses(0);
        userService.addUser(user.getFullName(), user.getEmail(), user.getPasswordHash(), user.getPhoneNumber(),
                user.getBonuses());
        return "redirect:/login";
    }
}
