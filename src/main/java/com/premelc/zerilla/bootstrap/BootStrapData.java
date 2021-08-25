package com.premelc.zerilla.bootstrap;

import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.Organiser;
import com.premelc.zerilla.models.User;
import com.premelc.zerilla.repositories.EventRepository;
import com.premelc.zerilla.repositories.OrganiserRepository;
import com.premelc.zerilla.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final OrganiserRepository organiserRepository;

    public BootStrapData(UserRepository userRepository, EventRepository eventRepository , OrganiserRepository organiserRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.organiserRepository = organiserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User eric = new User("destro" , "destro");
        Event party = new Event("Krizevci party" , "11ddf2");
        party.setShortDesc("Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party.setLongDesc("Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party.setType("Cajke");
        party.setArtistDesc("Nikolija je poznata cajkasica");
        party.setLocation("Krizevci");
        party.setAboutLocation("KRIZEVCI SU NAJBOLJI GRAD NA SVIJETU, TAMO SU NAJBOLJE FEŠTE I NAJJEFTINIJA CUGA");
        party.setEmbeddedVid("https://www.youtube.com/embed/p5WSWEYrjII");
        Organiser organiser = new Organiser("Premo");
        Organiser organiser2 = new Organiser("rod");
        organiser.setAboutOrganiser("Premo je student računarstva na tehnickom fakultetu sveucilista u rijeci");

        organiserRepository.save(organiser2);
        organiserRepository.save(organiser);
        userRepository.save(eric);
        eventRepository.save(party);

        eric.getEvents().add(party);
        party.getUsers().add(eric);

        party.setOrganiser(organiser2);
        organiser.getEvents().add(party);



        User rod = new User("rod" , "123456");
        Event party2 = new Event("Rijeka party" , "235ssf");

        rod.getEvents().add(party2);
        rod.getEvents().add(party);
        party2.getUsers().add(rod);
        party2.setOrganiser(organiser);
        party.getUsers().add(rod);
        organiser.getEvents().add(party2);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rod.getPassword());
        rod.setPassword(encodedPassword);


        party2.setShortDesc("Ludi party u Rijeci ovaj petak ajmo ekipa");
        party2.setLongDesc("Ludi party u Rijeci ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party2.setType("Cajke");
        party2.setArtistDesc("Tozla je neki random tipac");
        party2.setLocation("Rijeka");
        party2.setAboutLocation("Rijeka je grad kraj istre na moru ima puno brodova i studenta");
        party2.setEmbeddedVid("https://www.youtube.com/embed/Oy9cClTTdeI");


        userRepository.save(rod);
        eventRepository.save(party2);
        organiserRepository.save(organiser);
        userRepository.save(eric);
        eventRepository.save(party);

        System.out.println("Welcome to springboot");
        System.out.println("Number of organisers: " + organiserRepository.count());
        System.out.println("Number of events: " + eventRepository.count());
        System.out.println("Number of events organised by " +organiser.getUsername() +": " + organiser.getEvents().size());
        System.out.println("Username: " +userRepository.findByUsername("rod").getUsername() + "   Password: " + userRepository.findByUsername("rod").getPassword());
        System.out.println(eventRepository.findById(Integer.toUnsignedLong(3)));
    }
}
