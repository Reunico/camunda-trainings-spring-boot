package com.cbr.bpm.service;

import com.cbr.bpm.model.Order;

import java.util.Date;

public interface OrderService {
    Order persistOrder(Order order,
                       String description,
                       String contractor,
                       Date orderDate,
                       String customerName,
                       String title,
                       Long amount
                       );
}
