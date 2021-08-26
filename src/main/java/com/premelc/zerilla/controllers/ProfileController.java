package com.premelc.zerilla.controllers;

import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.InvitationRepository;
import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;

    public ProfileController(UserRepository userRepository , InvitationRepository invitationRepository) {
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
    }

    @RequestMapping("/my_profile")
    public String processRequest( String username , Model model){
        model.addAttribute("users" , userRepository.findAll());
        model.addAttribute("username" ,username);
        model.addAttribute("invites" , invitationRepository.findAll());
        return "action/profile";
    }

    @RequestMapping("/editProfile")
    public String editProfile(String username , Model model){

        model.addAttribute("user" , userRepository.findByUsername(username));
        return "/action/editProfile";
    }

    @RequestMapping("/saveChanges")
    public String saveChanges(User user ,String trueUsername, Model model){

        User activeUser = userRepository.findByUsername(trueUsername);

        activeUser.setUsername(user.getUsername());
        activeUser.setShortDesc(user.getShortDesc());
        activeUser.setFavQuote(user.getFavQuote());
        activeUser.setFavArtist(user.getFavArtist());
        activeUser.setCountry(user.getCountry());
        activeUser.setOccupation(user.getOccupation());
        activeUser.setAnthem(user.getAnthem());
        activeUser.setHobby(user.getHobby());

        userRepository.save(activeUser);

        model.addAttribute("users" , userRepository.findAll());
        model.addAttribute("username" ,user.getUsername());
        model.addAttribute("invites" , invitationRepository.findAll());
        return "/action/profile";
    }
}