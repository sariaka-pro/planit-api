package com.descodeuses.planit.dto;

import java.time.LocalDate;
import java.util.Set;


public class ActionDTO {

    private Long id; 
    private String title;
    private boolean completed; 
    private LocalDate dueDate; 
    private String priorities;
    private Set<Long> memberIds; 



    public ActionDTO(Long id,String title,boolean completed,LocalDate dueDate, String priorities) {
        this.id = id; 
        this.title = title; 
        this.completed = completed; 
        this.dueDate = dueDate; 
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

    public String getPriorities() {
        return priorities; 
    }

    public void setPriorities(String priorities) {
        this.priorities = priorities;
    }

    public Set<Long> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Set<Long> memberIds) {
        this.memberIds = memberIds;
    }
}
