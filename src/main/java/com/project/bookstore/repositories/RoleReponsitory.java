package com.project.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookstore.models.Role;

public interface RoleReponsitory extends JpaRepository<Role, Long> {
    
}
