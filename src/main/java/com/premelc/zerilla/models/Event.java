package com.premelc.zerilla.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String code;
    private String shortDesc;
    private String longDesc;
    private String artist;
    private String artistDesc;
    private String type;
    private String embeddedVid;
    private String location;
    private String aboutLocation;
    private String date;
    //true ++ public , false -- private
    private Boolean privacy = true;

    @ManyToOne
    private Organiser organiser;

    @ManyToMany
    @JoinTable(name="user_event" , joinColumns = @JoinColumn(name = "event_id") , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Event() {
    }

    public Event(String title, String code, String shortDesc, String longDesc, String type, String embeddedVid, Organiser organiser , String artistDesc) {
        this.privacy = true;
        this.title = title;
        this.code = code;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.artistDesc = artistDesc;
        this.type = type;
        this.embeddedVid = embeddedVid;
        this.organiser = organiser;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Event(String title, String code) {
        this.title = title;
        this.code = code;
    }

    /*public Event(Optional<Event> event) {
        this.title = event.
    }*/

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtistDesc() {
        return artistDesc;
    }

    public void setArtistDesc(String artistDesc) {
        this.artistDesc = artistDesc;
    }

    public String getAboutLocation() {
        return aboutLocation;
    }

    public void setAboutLocation(String aboutLocation) {
        this.aboutLocation = aboutLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmbeddedVid() {
        return embeddedVid;
    }

    public void setEmbeddedVid(String embeddedVid) {
        this.embeddedVid = embeddedVid;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", artistDesc='" + artistDesc + '\'' +
                ", type='" + type + '\'' +
                ", embeddedVid='" + embeddedVid + '\'' +
                ", organiser=" + organiser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id != null ? id.equals(event.id) : event.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
