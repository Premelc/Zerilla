package com.premelc.zerilla.controllers;

import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.Invitation;
import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.EventRepository;
import com.premelc.zerilla.repositories.InvitationRepository;
import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InviteController {

    UserRepository userRepository;
    EventRepository eventRepository;
    InvitationRepository invitationRepository;

    public InviteController(UserRepository userRepository, EventRepository eventRepository, InvitationRepository invitationRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.invitationRepository = invitationRepository;
    }

    @RequestMapping("/EventInvite")
    public String invite(Long id ,String username, String inviteUsername,String eventTitle ,Model model){
        User invitedUser = userRepository.findByUsername(inviteUsername);
        User activeUser = userRepository.findByUsername(username);
        Event event = eventRepository.findByTitle(eventTitle);


        if (invitedUser != null){
            if (invitedUser.getUsername() != username) {

                Invitation inv = new Invitation(activeUser, invitedUser, event);
                invitationRepository.save(inv);

                invitedUser.getInvitations().add(inv);
                userRepository.save(invitedUser);

                event.getInvitations().add(inv);
                eventRepository.save(event);

                model.addAttribute("message", "User invited to event!");
            }else if(invitedUser.getUsername() == username){
                model.addAttribute("message" , "Can't invite yourself to your own event. You are the host after all...");
            }
        }
        else{
            model.addAttribute("message" , "User doesn't exist!");
        }

        model.addAttribute("events" , eventRepository.findAll());
        model.addAttribute("id" , id);

        return("events/specificEvent");
    }

}
