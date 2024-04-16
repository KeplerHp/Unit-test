package com.bookstore.serviceimpl;

import com.bookstore.dao.OrderDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class OrderServiceImplTest {
    @Mock
    OrderDao orderDao;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        orderService.setOrderDao(orderDao);
    }

    @Test
    void setOrderDao() {
        assertSame(orderDao, orderService.orderDao);
    }

    @Test
    void getOrder() {
        orderService.getOrder();
        Mockito.verify(orderDao, times(1)).getOrder();
    }

    @Test
    void getOrderByOrderId() {
        Integer id = 1;
        orderService.getOrderByOrderId(id);
        Mockito.verify(orderDao, times(1)).getOrderByOrderId(id);
    }

    @Test
    void getAllOrder() {
        orderService.getAllOrder();
        Mockito.verify(orderDao, times(1)).getAllOrder();
    }

    @Test
    void getOrderByTime() {
        String t1 = "10000";
        String t2 = "20000";
        orderService.getOrderByTime(t1, t2);
        Mockito.verify(orderDao, times(1)).getOrderByTime(t1, t2);
    }

    @Test
    void getAllOrderByTime() {
        String t1 = "10000";
        String t2 = "20000";
        orderService.getAllOrderByTime(t1, t2);
        Mockito.verify(orderDao, times(1)).getAllOrderByTime(t1, t2);
    }
}