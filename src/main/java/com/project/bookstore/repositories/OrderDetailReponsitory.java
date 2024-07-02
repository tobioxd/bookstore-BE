package com.project.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookstore.models.OrderDetail;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail,Long> {
    
    List<OrderDetail> findByOrderId(Long orderId);

} 
