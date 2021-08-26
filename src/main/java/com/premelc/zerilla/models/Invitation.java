package com.premelc.zerilla.models;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Invitation {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne
        private User invited;

        @ManyToOne
        private Event invitedTo;

        private String invitationCode;

    public Invitation() {
    }

    public Invitation(User invitedFrom, User invited, Event invitedTo) {
        this.invitationCode = generateInvCode();
        this.invited = invited;
        this.invitedTo = invitedTo;
    }

    public static String generateInvCode(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInvited() {
        return invited;
    }

    public void setInvited(User invited) {
        this.invited = invited;
    }

    public Event getInvitedTo() {
        return invitedTo;
    }

    public void setInvitedTo(Event invitedTo) {
        this.invitedTo = invitedTo;
    }
}
