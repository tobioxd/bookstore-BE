package com.project.bookstore.controllers;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dtos.OrderDTO;
import com.project.bookstore.models.Order;
import com.project.bookstore.models.User;
import com.project.bookstore.services.OrderService;
import com.project.bookstore.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createOrder(
        @Valid @RequestBody OrderDTO orderDTO,
        BindingResult result,
        @RequestHeader("Authorization") String authorizationHeader) {
        try{
            String extractedToken = authorizationHeader.substring(7); // Loại bỏ "Bearer " từ chuỗi token
            User user = userService.getUserDetailsFromToken(extractedToken);
            orderDTO.setUserid(user.getId());
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage) 
                        .toList();
                
                return ResponseEntity.badRequest().body(errorMessages);
            }
            // return ResponseEntity.ok(orderDTO.getUserid());
            Order order = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(order);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<? > getOrders(@Valid @PathVariable("user_id") Long user_id) {
        try {
            return ResponseEntity.ok(orderService.getAllOrders(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }  

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
        try {
            orderService.updateOrder(id, orderDTO);
            return ResponseEntity.ok("Order updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@Valid @PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } 

}
