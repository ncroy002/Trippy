package com.trippy.back.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "event_comment")
public class EventComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String commentText;

    @ManyToOne(targetEntity = Account.class)
    Account account;

    @ManyToOne(targetEntity =  Event.class)
    Event event;

    public EventComment() {
    }

    public EventComment(int id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventComment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventComment that = (EventComment) o;
        return id == that.id &&
                Objects.equals(commentText, that.commentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentText);
    }
}
