package com.trippy.back.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trippy.back.enumeration.ERole;

import javax.persistence.*;
import java.util.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String username;

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
    String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    List<TripList> trips;

    @OneToMany(targetEntity = Event.class,cascade = CascadeType.ALL )
    List<Event> events;

    public String getUsername() {
        return username;
    }

    public void setUsername(String un) {
        this.username = un;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(ERole r) {
        this.role = r.toString();
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

    public List<TripList> getTrips() {
        return trips;
    }

    public void setTrips(List<TripList> trips) {
        this.trips = trips;
    }

    @JsonIgnore
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Account() {

    }

    public Account(String email, String firstName, String lastName, String encodedPassword ){
        setEmail(email);
        setUsername(email);
        setPassword(encodedPassword);
        setFirstName(firstName);
        setLastName(lastName);
        setDescription("");
        setProfileImage("");
        setBannerImage("");
        setRole(ERole.ROLE_USER);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, description, firstName, lastName);
    }
}

