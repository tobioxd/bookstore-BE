package com.project.bookstore.services;

import java.util.List;

import com.project.bookstore.dtos.OrderDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Order;

public interface IOrderService {

    Order createOrder(OrderDTO orderDTO) throws DataNotFoundException;

    Order getOrderById(Long id) throws DataNotFoundException;

    Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;

    void deleteOrder(Long id) throws DataNotFoundException;

    List<Order> getAllOrders(Long userId);

}
