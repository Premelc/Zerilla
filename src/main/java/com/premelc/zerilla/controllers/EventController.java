package com.premelc.zerilla.controllers;

import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/events")
    public String getEvents(Model model){

        model.addAttribute("events" , eventRepository.findAll());

        return "events/discover";
    }

    @RequestMapping("/search")
    public String search(Model model , String searchUsername , String searchLocation , String searchType , String searchDate , String searchCode){
        Set<Event> events = new HashSet<>();

        events.addAll(eventRepository.findAllByCode(searchCode));
        events.addAll(eventRepository.findAllByOrganiser(searchUsername));
        events.addAll(eventRepository.findAllByLocation(searchLocation));
        events.addAll(eventRepository.findAllByType(searchType));
        events.addAll(eventRepository.findAllByDate(searchDate));

        model.addAttribute("state" , true);
        model.addAttribute("events" , events);

        return "events/discover";
    }

}
