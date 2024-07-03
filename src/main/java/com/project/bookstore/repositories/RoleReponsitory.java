package com.project.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookstore.models.Role;

@Repository
public interface RoleReponsitory extends JpaRepository<Role, Long> {
    
}
