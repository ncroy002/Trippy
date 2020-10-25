package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Help{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String question;

    @Column
    String name;

    @Column
    String email;

    @Column
    Boolean completed;

    public Help() {
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question= question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    public Help(String question,  String name, String email, Boolean completed ){
        setQuestion(question);
        setName(name);
        setEmail(email);
        setCompleted(completed);
    }



}


