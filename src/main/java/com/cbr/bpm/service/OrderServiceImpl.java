package com.cbr.bpm.service;

import com.cbr.bpm.model.Order;
import com.cbr.bpm.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order persistOrder(
            Order order,
            String description,
            String contractor,
            Date orderDate,
            String customerName,
            String title,
            Long amount
            ) {
        if (order == null) { order = new Order(); }

        order.setDescription(description);
        order.setContractor(contractor);
        order.setOrderDate(orderDate);
        order.setFullName(customerName);
        order.setTitle(title);
        order.setAmount(BigDecimal.valueOf(amount));
        return orderRepository.save(order);
    }
}
