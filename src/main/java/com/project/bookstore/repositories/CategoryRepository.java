package com.project.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookstore.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long > {

    
} 
