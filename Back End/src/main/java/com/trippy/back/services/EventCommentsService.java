package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.Event;
import com.trippy.back.entities.EventComment;
import com.trippy.back.repos.EventCommentsRepo;
import com.trippy.back.repos.EventsRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventCommentsService {

    @Autowired
    EventCommentsRepo commentsRepo;

    @Autowired
    EventsRepo eventsRepo;

    @Autowired
    UserRepo userRepo;

    public EventComment saveComment(EventComment eventComment, String email, int eventId){
        Optional<Event> foundEvent = eventsRepo.findById(eventId);

        if(foundEvent.isPresent()){
            Event event = foundEvent.get();
            Account account = userRepo.findByEmail(email);

            if(account != null) {
                eventComment.setAccount(account);
                eventComment.setEvent(event);
                commentsRepo.save(eventComment);
                return eventComment;
            }

            throw new RuntimeException("Account with email "+ email + " not found");
        }

        throw new RuntimeException("Event with ID "+eventId+" not found!");
    }

    public List<EventComment> findComments(int eventId){
        Optional<Event> event = eventsRepo.findById(eventId);

        if(event.isPresent()){
            return commentsRepo.findAll().stream().filter(c -> c.getEvent().getId() == eventId).collect(Collectors.toList());
        }

        throw new RuntimeException("No such event "+eventId+" exists.");
    }

    public EventComment deleteComment(int eventCommentId){
        Optional<EventComment> foundEventComment = commentsRepo.findById(eventCommentId);

        if(foundEventComment.isPresent()){
            EventComment eventComment = foundEventComment.get();
            commentsRepo.delete(eventComment);
            return eventComment;
        }

        throw new RuntimeException("Failed to delete the event with id "+eventCommentId);
    }
}
