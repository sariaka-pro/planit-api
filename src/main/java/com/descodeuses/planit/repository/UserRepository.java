package com.descodeuses.planit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.descodeuses.planit.entity.DCUser;

public interface UserRepository extends JpaRepository<DCUser, Long> {
    Optional<DCUser> findByUsername(String username);
}
