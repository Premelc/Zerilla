package com.premelc.zerilla.controllers;

import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.Organiser;
import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.EventRepository;
import com.premelc.zerilla.repositories.OrganiserRepository;
import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
public class HostController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final OrganiserRepository organiserRepository;
    private static Event ev;

    public HostController(UserRepository userRepository , EventRepository eventRepository , OrganiserRepository organiserRepository) {

        this.organiserRepository = organiserRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    @RequestMapping("/host")
    public String getHost(Model model){
        model.addAttribute("event" , new Event());
        return "events/host";
    }

    @PostMapping("/process_hosting2")
    public String processHost2(Event event , Model model , String username , String aboutOrganiser)
    {
        System.out.println(event.getTitle());
        this.ev = event;
        Organiser org = new Organiser(username , aboutOrganiser);
        organiserRepository.save(org);
        this.ev.setOrganiser(org);
        model.addAttribute("event2" , event);
        return"events/hostArtist";
    }
    @PostMapping("/process_hosting3")
    public String processHost3(Event event , Model model)
    {
        this.ev.setArtist(event.getArtist());
        this.ev.setArtistDesc(event.getArtistDesc());
        this.ev.setEmbeddedVid(event.getEmbeddedVid());

        System.out.println(ev.getTitle());
        model.addAttribute("event3" , event);
        return"events/hostLocation";
    }

    @PostMapping("/process_hosting_finish")
    public String processHost4(Event event ,Model model ,String username){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        event.setCode(generatedString);

        this.ev.setLocation(event.getLocation());
        this.ev.setAboutLocation(event.getAboutLocation());
        this.ev.setCode(event.getCode());

        model.addAttribute("creation" , 1);
        model.addAttribute("users" , userRepository.findAll());
        System.out.println(ev.getTitle());

        User activeUser = userRepository.findByUsername(username);

        activeUser.getEvents().add(this.ev);
        this.ev.getUsers().add(activeUser);

        eventRepository.save(ev);
        this.ev = null;
        return "action/profile";
    }

    @RequestMapping("/specific_event_added")
    public String getSuccessfullyAddedEvent(Model model , String title , String username){
        User activeUser = userRepository.findByUsername(username);
        Event event = eventRepository.findByTitle(title);
        for (User user:event.getUsers()
             ) {
            System.out.println(user.getUsername());
        }

        if (event.getUsers().contains(activeUser)){
            System.out.println("\"Event already on my events list\"");
            model.addAttribute("message" , "Event already on my events list");
        }else{
            event.getUsers().add(activeUser);
            activeUser.getEvents().add(event);
            userRepository.save(activeUser);
            eventRepository.save(event);
            model.addAttribute("message" , "Event successfully added to my events list");
            System.out.println("Event successfully added to my events list");
        }

        model.addAttribute("events" , eventRepository.findAll());

        return "events/discover";
    }

}
