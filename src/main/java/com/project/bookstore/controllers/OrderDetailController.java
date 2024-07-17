package com.project.bookstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.project.bookstore.dtos.OrderDetailDTO;
import com.project.bookstore.services.OrderDetailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> createOrderDetail(
        @Valid @RequestBody OrderDetailDTO orderDetailDTO,
        BindingResult result) {
        try{
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage) 
                        .toList();

                return ResponseEntity.badRequest().body(errorMessages);
            }
            
            orderDetailService.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok("Order detail created successfully");
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
            
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_user')")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("id " + id);
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_user')")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId) {
        try{
            return ResponseEntity.ok(orderDetailService.getAllOrderDetails(orderId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> updateOrderDetail(@Valid @PathVariable("id") Long id,
            @Valid @RequestBody OrderDetailDTO orderDetailDTO) {
            try{
                orderDetailService.updateOrderDetail(id, orderDetailDTO);
                return ResponseEntity.ok("Order detail updated successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<String> deleteORderDetail(@Valid @PathVariable("id") Long id) {
        try{
            orderDetailService.deleteOrderDetail(id);
            return ResponseEntity.ok("Order detail deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
