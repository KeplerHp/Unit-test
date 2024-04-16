package com.bookstore.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    User user = new User();
    Timestamp time = new Timestamp(10000);
    BigDecimal price = new BigDecimal("19.99");
    List<OrderItem> orderItem = new ArrayList<>();

    @Test
    void orderTest() {
        Order testOrder = new Order();

        testOrder.setOrderId(1);
        testOrder.setUser(user);
        testOrder.setTime(time);
        testOrder.setPrice(price);
        testOrder.setOrderItem(orderItem);

        assertEquals(1, testOrder.getOrderId());
        assertEquals(user, testOrder.getUser());
        assertEquals(time, testOrder.getTime());
        assertEquals(price, testOrder.getPrice());
        assertEquals(orderItem, testOrder.getOrderItem());
    }
}