package com.bookstore.controller;

import com.bookstore.entity.OrderItem;
import com.bookstore.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

class OrderItemControllerTest {

    @Mock
    OrderItemService orderItemService;

    OrderItemController orderItemController;
    List<OrderItem> test_orderitem_list=new ArrayList<OrderItem>();

    @BeforeEach
    void initTest(){
        this.orderItemService= Mockito.mock(OrderItemService.class);
        this.orderItemController=new OrderItemController(this.orderItemService);
        lenient().when(this.orderItemService.getOrderItemById(any(Integer.class))).thenReturn(this.test_orderitem_list);
    }

    @Test
    void getOrderItemById() {
        assertEquals(this.orderItemController.getOrderItemById(1),this.test_orderitem_list);
    }
}