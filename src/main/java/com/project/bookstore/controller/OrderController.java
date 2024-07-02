package com.project.bookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dtos.OrderDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/orders")

public class OrderController {

    @PostMapping("")
    public ResponseEntity<?> createOrder(
        @Valid @RequestBody OrderDTO orderDTO,
        BindingResult result) {
        try{
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage) 
                        .toList();
                
                return ResponseEntity.badRequest().body(errorMessages);
            }

            return ResponseEntity.ok("createOrder success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<String > getOrders(@Valid @PathVariable("user_id") Long user_id) {
        try {
            return ResponseEntity.ok("getOrders with user_id: " + user_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("update order " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("Order deleted successfully");
    } 

}
