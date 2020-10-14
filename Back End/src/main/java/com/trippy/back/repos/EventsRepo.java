package com.trippy.back.repos;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventsRepo extends JpaRepository<Event, Integer> {
//    Optional<Event> findByIDOptional(int ID);
//    Boolean existsByID(int ID);
//    Event findByID(int ID);
}
