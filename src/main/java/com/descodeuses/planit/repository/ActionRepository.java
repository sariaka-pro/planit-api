package com.descodeuses.planit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.descodeuses.planit.entity.Action;

/// Extends Jpa... <OBJECT, TYPE ID PROJECT> 
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {


}
