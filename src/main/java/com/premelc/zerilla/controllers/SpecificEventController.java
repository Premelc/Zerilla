package com.premelc.zerilla.controllers;


import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.EventRepository;
import com.premelc.zerilla.repositories.InvitationRepository;
import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpecificEventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;


    public SpecificEventController(EventRepository eventRepository , UserRepository userRepository , InvitationRepository invitationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
    }

    @RequestMapping("/specific_event")
    public String processRequest(Long id , Model model){
        System.out.println(id);
        model.addAttribute("events" , eventRepository.findAll());
        model.addAttribute("id" , id);

        return "events/specificEvent";
    }

    @RequestMapping("/specificEventRemoval")
    public String removeUser(String eventTitle , String userUsername , String removed_user ,Long id,Model model){

        System.out.println(eventTitle);
        User removedUser = userRepository.findByUsername(removed_user);
        Event event = eventRepository.findByTitle(eventTitle);
        System.out.println(event.getTitle());
        for (User user:event.getUsers()
        ) {
            System.out.println(user.getUsername());
        }

        if (event.getUsers().contains(removedUser)){
            System.out.println("\"Found user to remove\"");
            event.getUsers().remove(removedUser);
            removedUser.getEvents().remove(event);
            userRepository.save(removedUser);
            eventRepository.save(event);
            model.addAttribute("message" , "Removed user successfully");
        }else{
            model.addAttribute("message" , "User not on event atendees list");
            System.out.println("User not on event atendees list");
        }
        model.addAttribute("events" , eventRepository.findAll());
        model.addAttribute("id" , id);

        return "events/specificEvent";
    }

    @RequestMapping("/make_private")
    public String makeEventPrivate(String eventTitle ,Long id,Model model){
        System.out.println(eventTitle);
        Event event = eventRepository.findByTitle(eventTitle);
        System.out.println(event.getTitle());

        if (!event.getPrivacy()){
            model.addAttribute("message" , "Event is already private");
        }else{
            event.setPrivacy(false);
            eventRepository.save(event);
            model.addAttribute("message" , "Selected event is now private");
        }

        model.addAttribute("events" , eventRepository.findAll());
        model.addAttribute("id" , id);
        return "events/specificEvent";
    }
    @RequestMapping("/make_public")
    public String makeEventPublic(String eventTitle ,Long id,Model model){
        System.out.println(eventTitle);
        Event event = eventRepository.findByTitle(eventTitle);
        System.out.println(event.getTitle());

        if (event.getPrivacy()){
            model.addAttribute("message" , "Event is already public");
        }else{
            event.setPrivacy(true);
            eventRepository.save(event);
            model.addAttribute("message" , "Selected event is now public");
        }

        model.addAttribute("events" , eventRepository.findAll());
        model.addAttribute("id" , id);
        return "events/specificEvent";
    }

    @RequestMapping("/unlistEvent")
    public String unlistEvent(String eventTitle ,Long id,Model model){
        Event event = eventRepository.findByTitle(eventTitle);

        event.setPrivacy(false);
        event.getUsers().clear();
        event.setUnlisted(true);

        model.addAttribute("message" , "event successfully unlisted");
        model.addAttribute("events" , eventRepository.findAll());

        return "events/discover";
    }

}
