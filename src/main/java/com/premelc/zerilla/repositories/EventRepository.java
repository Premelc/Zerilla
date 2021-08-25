package com.premelc.zerilla.repositories;

import com.premelc.zerilla.models.Event;
import com.premelc.zerilla.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EventRepository extends CrudRepository<Event,Long> {
    @Query("SELECT e FROM Event e WHERE e.title = ?1")
    Event findByTitle(String title);

    @Query("SELECT e FROM Event e WHERE e.organiser.username = ?1")
    Set<Event> findAllByOrganiser(String organiserUsername);

    @Query("SELECT e FROM Event e WHERE e.code = ?1")
    Set<Event> findAllByCode(String code);

    @Query("SELECT e FROM Event e WHERE e.location = ?1")
    Set<Event> findAllByLocation(String location);

    @Query("SELECT e FROM Event e WHERE e.type = ?1")
    Set<Event> findAllByType(String type);

    @Query("SELECT e FROM Event e WHERE e.date = ?1")
    Set<Event> findAllByDate(String date);
}
