package com.project.bookstore.services;

import java.util.List;

import com.project.bookstore.dtos.OrderDetailDTO;
import com.project.bookstore.exceptions.DataAlreadyExistsException;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.OrderDetail;

public interface IOrderDetailService {

    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException, DataAlreadyExistsException;

    OrderDetail getOrderDetailById(Long id) throws DataNotFoundException;

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException;

    void deleteOrderDetail(Long id) throws DataNotFoundException;

    List<OrderDetail> getAllOrderDetails(Long orderId);

}
