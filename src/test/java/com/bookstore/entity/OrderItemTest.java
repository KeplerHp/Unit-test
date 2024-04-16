package com.bookstore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class OrderItemTest {
    Book book = new Book();

    @Test
    void OrderItemTest() {
        OrderItem testOrderItem = new OrderItem();

        testOrderItem.setOrderId(1);
        testOrderItem.setBookId(1);
        testOrderItem.setAmount(10);
        testOrderItem.setBook(book);

        assertEquals(1, testOrderItem.getOrderId());
        assertEquals(1, testOrderItem.getBookId());
        assertEquals(10, testOrderItem.getAmount());
        assertEquals(book, testOrderItem.getBook());
    }
}


class PrimaryKeyOrderItemTest {
    @Test
    void PrimaryKeyOrderItemTest() {
        PrimaryKey_OrderItem testOrderItem = new PrimaryKey_OrderItem();

        testOrderItem.setOrderId(1);
        testOrderItem.setBookId(1);

        assertEquals(1, testOrderItem.getOrderId());
        assertEquals(1, testOrderItem.getBookId());
    }
}
