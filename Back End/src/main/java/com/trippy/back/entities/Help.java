package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Help{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String message;

    @Column
    Boolean completed;

    @Column
    Long userId;

    public Help() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message= message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Help(Long id, String message, Boolean completed, Long userId ){
        this.id = id;
        this.message = message;
        this.completed = completed;
        this.userId = userId;
    }



}


