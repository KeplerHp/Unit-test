package com.bookstore.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatTest {

    BigDecimal price = new BigDecimal("19.99");

    @Test
    void orderStatTest() {
        OrderStat testOrderStat = new OrderStat();

        testOrderStat.setBookId(1);
        testOrderStat.setName("test");
        testOrderStat.setAmount(10);
        testOrderStat.setPrice(price);

        assertEquals(1, testOrderStat.getBookId());
        assertEquals("test", testOrderStat.getName());
        assertEquals(10, testOrderStat.getAmount());
        assertEquals(price, testOrderStat.getPrice());
    }
}