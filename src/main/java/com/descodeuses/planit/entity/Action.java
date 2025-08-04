package com.descodeuses.planit.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity  // pour lui faire comprendre que ça sera une table. 
@Table(name="todo")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /// on spécifie que l'id va être générer automatiquement
    private Long id; 

    @Column(nullable = false)
    private String title;
    private boolean completed; 
    private LocalDate dueDate; 
    private String priorities; 
    private String projet; 

    @ManyToMany
    @JoinTable(  /// obligatoire lorsque l'on a une relation @ManytoMany. 
        name = "todo_contact",
        joinColumns = @JoinColumn(name = "todo_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id")
    )

    private Set<contact> members = new HashSet<>(); 

    public Set<contact> getMembers() {
        return members;
    }

    public void setMembers(Set<contact> members) {
        this.members = members;
    }

    public String getPriorities() {
        return priorities;
    }

    public void setPriorities(String priorities) {
        this.priorities = priorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }
  

}
