package com.cbr.bpm.service;

import com.cbr.bpm.model.Order;
import com.cbr.bpm.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order persistOrder(Order order, String description, String contractor, Date orderDate) {
        order.setDescription(description);
        order.setContractor(contractor);
        order.setOrderDate(orderDate);
        return orderRepository.save(order);
    }
}
