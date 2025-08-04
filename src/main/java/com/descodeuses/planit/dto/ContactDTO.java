package com.descodeuses.planit.dto;

import java.util.HashSet;
import java.util.Set;
import com.descodeuses.planit.entity.Action; 


public class ContactDTO {


    private Long id; 
    private String nom; 
    private String prenom; 

    public ContactDTO() {
    }

    public ContactDTO(Long id, String nom, String prenom) {
        this.id = id; 
        this.nom = nom; 
        this.prenom = prenom; 

    }

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

    private Set<Action> todos = new HashSet<>();

    public Set<Action> getTodos() {
        return todos; 
    }

    public void setTodos(Set<Action> todos) {
        this.todos = todos; 
    }



}
