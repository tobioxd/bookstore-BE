package com.project.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookstore.models.Order;

@Repository
public interface OrderReponsitory extends JpaRepository<Order , Long> {

    List<Order> findByUserId(Long userId);

}
