package com.project.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookstore.models.User;

@Repository
public interface UserReponsitory extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
