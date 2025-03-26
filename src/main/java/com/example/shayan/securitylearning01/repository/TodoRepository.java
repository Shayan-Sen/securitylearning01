package com.example.shayan.securitylearning01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, String> {
    
}
