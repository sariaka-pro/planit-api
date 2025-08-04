package com.descodeuses.planit.entity;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String nom; 
    private String prenom;
    private String age; 

    public Long getId() {
        return id; 
    }

    public void setId(Long id) {
        this.id = id; 
    }
    
    
    public String getNom() {
        return nom; 
    }

    public void setNom(String nom) {
        this.nom = nom; 
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @ManyToMany(mappedBy = "members")
    private Set<Action> todos = new HashSet<>();

    public Set<Action> getTodos() {
        return todos; 
    }

    public void setTodos(Set<Action> todos) {
        this.todos = todos; 
    }

}
