package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Faq{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String message;

//    @Column
//    String description;


    public Faq() {
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

//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public Faq(String message){
        setMessage(message);
//        setDescription("");
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faq faq = (Faq) o;
        return Objects.equals(id, faq.id) &&
                Objects.equals(message, faq.message);
    }
}


