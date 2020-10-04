package com.trippy.back.entities;

import javax.persistence.Entity;

@Entity
public class Admin extends Account {
    String role = "1"; //admin users have a role of one

    public String getRole() {
        return role;
    }
}
