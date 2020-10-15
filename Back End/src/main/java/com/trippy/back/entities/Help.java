package com.trippy.back.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String message;

    @Column
    Boolean completed ;

    public Help() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message= message;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed= completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Help(String message){
        setMessage(message);

    }
    public Help(Boolean completed){
        setCompleted(completed);

    }


    @Override
    public String toString() {
        return "Help{" +
                "id=" + id +
                "completed=" + completed +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Help help = (Help) o;
        return Objects.equals(id, help.id) &&
                Objects.equals(message, help.message);
    }
}
