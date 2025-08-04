package com.descodeuses.planit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.descodeuses.planit.entity.contact;

@Repository
public interface ContactRepository extends JpaRepository<contact, Long> {

}
