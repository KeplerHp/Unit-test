package com.bookstore.serviceimpl;

import com.bookstore.dao.OrderItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class OrderItemServiceImplTest {

    @Mock
    OrderItemDao orderItemDao;
    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        orderItemService.setOrderItemDao(orderItemDao);
    }

    @Test
    void setOrderItemDao() {
        assertSame(orderItemDao, orderItemService.orderItemDao);
    }

    @Test
    void getOrderItemById() {
        Integer id = 1;
        orderItemService.getOrderItemById(id);
        Mockito.verify(orderItemDao, times(1)).getOrderItemById(id);
    }
}