package com.premelc.zerilla.controllers;

import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/my_profile")
    public String processRequest( String username , Model model){
        model.addAttribute("users" , userRepository.findAll());
        model.addAttribute("username" ,username);
        return "action/profile";
    }
}