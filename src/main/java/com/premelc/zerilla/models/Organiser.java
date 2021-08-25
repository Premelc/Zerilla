package com.premelc.zerilla.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organiser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String aboutOrganiser;

    @OneToMany
    @JoinColumn(name = "organiser_id")
    private Set<Event> events = new HashSet<>();

    public Organiser() {
    }

    public Organiser(String username) {
        this.username = username;
    }

    public Organiser(String username, String aboutOrganiser) {
        this.username = username;
        this.aboutOrganiser = aboutOrganiser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getAboutOrganiser() {
        return aboutOrganiser;
    }

    public void setAboutOrganiser(String aboutOrganiser) {
        this.aboutOrganiser = aboutOrganiser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Organiser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organiser organiser = (Organiser) o;

        return id != null ? id.equals(organiser.id) : organiser.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
