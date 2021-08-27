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

        String li = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

        User u1 = new User("DjFunky" , "djfunky");
        User u2 = new User("Elena" ,"elena");
        User u3 = new User("Andrea" , "andrea");
        User u4 = new User("Filip" , "filip");
        User u5 = new User("Andrej","andrej");
        User u6 = new User("Leon" , "leon");
        User u7 = new User("Martina" , "martina");
        User u8 = new User("Petra" , "petra");
        User u9 = new User("Marko" , "marko");
        User u10 = new User("Mihael" , "mihael");
        User u11 = new User("Tea" , "tea");
        User u12 = new User("Ana" , "ana");
        User u13 = new User("Karla" , "karla");
        User u14 = new User("Krizevci Events" , "krizevcievents");
        User u15 = new User("destro" , "destro");
        User u16 = new User("rod" , "123456");

        Organiser org1 = new Organiser("Krizevci Events");
        org1.setAboutOrganiser("Skupina nadobudnih mladih koji žele oživiti naš grad");
        Organiser org2 = new Organiser("Mihael");
        org2.setAboutOrganiser("Vozim kamion i vikendom organiziram fešte");
        Organiser org3 = new Organiser("Andrea");
        org3.setAboutOrganiser("Samo želim proslaviti rođendan :(((");
        Organiser org4 = new Organiser("Filip");
        org4.setAboutOrganiser("Filip je student računarstva na tehnickom fakultetu sveucilista u rijeci");
        Organiser org5 = new Organiser("rod");
        org5.setAboutOrganiser("Rod je neki random tip");

        organiserRepository.save(org1);
        organiserRepository.save(org2);
        organiserRepository.save(org3);
        organiserRepository.save(org4);
        organiserRepository.save(org5);

        Event party1 = new Event("Krizevci party" , "11ddf2");
        Event party2 = new Event("Rijeka party" , "235ssf");
        Event party3 = new Event("Luda fešta u Gornjoj Rijeci" , "Gornja Rijeka gori ajmo",li , "Dino Merlin" ,"Ekscentrican starcic pjeva pjesme bla bla" ,"koncert", "https://www.youtube.com/embed/rBnzAU0wOZA" , "Gornja Rijeka","Centar svijeta , bolja od Donje Rijeke",org2 );
        Event party4 = new Event("Moj 18. rođendan" , "Pozivam te na svoj 18. rođendan",li , "Moja playlista :)" ,"Slušat ćemo sve pomalo ali najviše cajke" ,"rođendan", "https://www.youtube.com/embed/d82BPHicxQQ" , "Sudovec","Vatrogasni dom u Sudovcu",org3 );
        Event party5 = new Event("Veliko Krizevacko Spravisce" , "25. Veliko krizevacko spravisce 2021.",li , "Severina , Lidija Bacic , Lepa Brena , Drazen Zecic" ,"Razni izvodjaci zabavljat ce vas ove godine na velikom krizevackom spraviscu" ,"koncert", "https://www.youtube.com/embed/9vNoEEv8JdU" , "Krizevci","Centar Krizevaca , na velikoj pozornici",org1 );
        Event party6 = new Event("Dino Merlin @ Latino Križevci" , "Dino merlin dolazi u Križevce prvi puta , ne propustite ovaj koncert",li , "Dino Merlin" ,"Ekscentrican starcic pjeva pjesme bla bla" ,"koncert", "https://www.youtube.com/embed/rBnzAU0wOZA" , "Krizevci","Nocni klub latino , najpopularnije mjesto za izlazak u krizevcima",org1 );


        party1.setShortDesc("Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party1.setLongDesc("Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party1.setType("Cajke");
        party1.setArtist("Nikolija");
        party1.setArtistDesc("Nikolija je poznata cajkasica");
        party1.setLocation("Krizevci");
        party1.setAboutLocation("KRIZEVCI SU NAJBOLJI GRAD NA SVIJETU, TAMO SU NAJBOLJE FEŠTE I NAJJEFTINIJA CUGA");
        party1.setEmbeddedVid("https://www.youtube.com/embed/p5WSWEYrjII");
        party1.setOrganiser(org4);

        party2.setShortDesc("Ludi party u Rijeci ovaj petak ajmo ekipa");
        party2.setLongDesc("Ludi party u Rijeci ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa Ludi party u Krizevcima ovaj petak ajmo ekipa");
        party2.setType("Cajke");
        party2.setArtist("Tozla");
        party2.setArtistDesc("Tozla je neki random tipac");
        party2.setLocation("Rijeka");
        party2.setAboutLocation("Rijeka je grad kraj istre na moru ima puno brodova i studenta");
        party2.setEmbeddedVid("https://www.youtube.com/embed/Oy9cClTTdeI");
        party2.setOrganiser(org5);

        eventRepository.save(party1);
        eventRepository.save(party2);
        eventRepository.save(party3);
        eventRepository.save(party4);
        eventRepository.save(party5);
        eventRepository.save(party6);

        org5.getEvents().add(party1);
        org2.getEvents().add(party2);
        org2.getEvents().add(party3);
        org3.getEvents().add(party4);
        org1.getEvents().add(party5);
        org1.getEvents().add(party6);

        organiserRepository.save(org1);
        organiserRepository.save(org2);
        organiserRepository.save(org3);
        organiserRepository.save(org4);
        organiserRepository.save(org5);

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);
        userRepository.save(u7);
        userRepository.save(u8);
        userRepository.save(u9);
        userRepository.save(u10);
        userRepository.save(u11);
        userRepository.save(u12);
        userRepository.save(u13);
        userRepository.save(u14);
        userRepository.save(u15);
        userRepository.save(u16);

        //Krizevci events u14
        u14.getEvents().add(party5);
        party5.getUsers().add(u14);

        u14.getEvents().add(party6);
        party6.getUsers().add(u14);

        //Andrea u3
        u1.getEvents().add(party4);
        party4.getUsers().add(u1);
        //Mihael u10
        u10.getEvents().add(party3);
        party3.getUsers().add(u10);
        //Filip u4
        u4.getEvents().add(party1);
        party1.getUsers().add(u4);
        //rod u16
        u16.getEvents().add(party2);
        party2.getUsers().add(u16);

        u10.getEvents().add(party4);
        party4.getUsers().add(u10);
        u11.getEvents().add(party4);
        party4.getUsers().add(u11);
        u13.getEvents().add(party4);
        party4.getUsers().add(u13);
        u15.getEvents().add(party4);
        party4.getUsers().add(u15);


        u1.getEvents().add(party3);
        party3.getUsers().add(u1);
        u2.getEvents().add(party3);
        party3.getUsers().add(u2);
        u3.getEvents().add(party3);
        party3.getUsers().add(u3);
        u4.getEvents().add(party3);
        party3.getUsers().add(u4);
        u5.getEvents().add(party3);
        party3.getUsers().add(u5);

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);
        userRepository.save(u7);
        userRepository.save(u8);
        userRepository.save(u9);
        userRepository.save(u10);
        userRepository.save(u11);
        userRepository.save(u12);
        userRepository.save(u13);
        userRepository.save(u14);
        userRepository.save(u15);
        userRepository.save(u16);

        eventRepository.save(party1);
        eventRepository.save(party2);
        eventRepository.save(party3);
        eventRepository.save(party4);
        eventRepository.save(party5);
        eventRepository.save(party6);


    }
}
