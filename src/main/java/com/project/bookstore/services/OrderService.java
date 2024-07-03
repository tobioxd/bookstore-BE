package com.project.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bookstore.dtos.OrderDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Order;
import com.project.bookstore.models.User;
import com.project.bookstore.repositories.OrderReponsitory;
import com.project.bookstore.repositories.UserReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final OrderReponsitory orderRepository;
    private final UserReponsitory userRepository;

    @Override
    public Order createOrder(OrderDTO orderDTO) throws DataNotFoundException {
        
        User user = userRepository.findById(orderDTO.getUserid())
                .orElseThrow(() -> new DataNotFoundException("User not found."));

        Order order = Order.builder()
                .phonenumber(orderDTO.getPhonenumber())
                .address(orderDTO.getAddress())
                .build();

        order.setUser(user);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) throws DataNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Order not found."));
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order existingOrder = getOrderById(id);

        if(existingOrder != null){
            existingOrder.setFullname(orderDTO.getFullname());
            existingOrder.setEmail(orderDTO.getEmail());
            existingOrder.setPhonenumber(orderDTO.getPhonenumber());
            existingOrder.setAddress(orderDTO.getAddress());
            existingOrder.setNote(orderDTO.getNote());
            existingOrder.setOrderDate(new java.sql.Date(orderDTO.getOrderdate().getTime()));
            existingOrder.setStatus(orderDTO.getStatus());
            existingOrder.setTotalPaid(orderDTO.getTotalpaid());
            existingOrder.setShippingMethod(orderDTO.getShippingmethod());
            existingOrder.setPaymentMethod(orderDTO.getPaymentmethod());
            existingOrder.setShippingDate(new java.sql.Date(orderDTO.getShippingdate().getTime()));

            return orderRepository.save(existingOrder);
        }

        return null;
    }

    @Override
    public void deleteOrder(Long id) throws DataNotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
        } else {
            throw new DataNotFoundException("Order not found.");
        }
    }

    @Override
    public List<Order> getAllOrders(Long userId) {
        return orderRepository.findByUserId(userId);    
    }

}
