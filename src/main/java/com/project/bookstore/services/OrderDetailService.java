package com.project.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bookstore.dtos.OrderDetailDTO;
import com.project.bookstore.exceptions.DataAlreadyExistsException;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Book;
import com.project.bookstore.models.Order;
import com.project.bookstore.models.OrderDetail;
import com.project.bookstore.repositories.BookReponsitory;
import com.project.bookstore.repositories.OrderDetailReponsitory;
import com.project.bookstore.repositories.OrderReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {

    private final OrderDetailReponsitory orderDetailRepository;
    private final OrderReponsitory orderRepository;
    private final BookReponsitory bookRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException, DataAlreadyExistsException {

        // Check if OrderDetail with the specified orderid and bookid already exists
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository
                .findByOrderIdAndBookId(orderDetailDTO.getOrderid(), orderDetailDTO.getBookid());
        if (existingOrderDetail.isPresent()) {
            throw new DataAlreadyExistsException("OrderDetail already exists with order ID "
                    + orderDetailDTO.getOrderid() + " and book ID " + orderDetailDTO.getBookid());
        }

        Order order = orderRepository.findById(orderDetailDTO.getOrderid())
                .orElseThrow(() -> new DataNotFoundException("Order not found."));

        Book book = bookRepository.findById(orderDetailDTO.getBookid())
                .orElseThrow(() -> new DataNotFoundException("Book not found."));

        OrderDetail orderDetail = OrderDetail.builder()
                .price(orderDetailDTO.getPrice())
                .num(orderDetailDTO.getNum())
                .build();

        orderDetail.setOrder(order);
        orderDetail.setBook(book);
        Float totalMoney = orderDetail.getPrice() * orderDetail.getNum();
        orderDetail.setTotalMoney(totalMoney);

        return orderDetailRepository.save(orderDetail);

    }

    @Override
    public OrderDetail getOrderDetailById(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("OrderDetail not found."));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        OrderDetail existingOrderDetail = getOrderDetailById(id);

        if (existingOrderDetail != null) {
            existingOrderDetail.setPrice(orderDetailDTO.getPrice());
            existingOrderDetail.setNum(orderDetailDTO.getNum());
            existingOrderDetail.setTotalMoney(orderDetailDTO.getPrice() * orderDetailDTO.getNum());

            return orderDetailRepository.save(existingOrderDetail);
        }

        return null;
    }

    @Override
    public void deleteOrderDetail(Long id) throws DataNotFoundException {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            orderDetailRepository.delete(orderDetail.get());
        } else {
            throw new DataNotFoundException("OrderDetail not found.");
        }
    }

    @Override
    public List<OrderDetail> getAllOrderDetails(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

}
