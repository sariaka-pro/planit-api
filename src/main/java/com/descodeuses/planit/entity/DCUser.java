package com.descodeuses.planit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DCUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String firstname;
    private String lastname;
    private String genre; 


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}