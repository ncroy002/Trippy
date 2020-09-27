package com.trippy.back.entities;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String email;

    @Column
    String password;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String description;

    @Column
    String profileImage;

    @Column
    String bannerImage;

    @Column
    String token;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }

    public Account() {

    }
    public Account(String email, String password, String firstName, String lastName){
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setDescription("");
        setProfileImage("");
        setBannerImage("");
    }
}
