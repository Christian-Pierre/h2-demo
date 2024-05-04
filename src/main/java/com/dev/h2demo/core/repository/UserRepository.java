package com.dev.h2demo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.h2demo.core.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
