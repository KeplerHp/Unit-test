package com.bookstore.daoimpl;

import com.bookstore.entity.OrderItem;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class OrderItemDaoImplTest {
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderItemDaoImpl orderItemDao;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        orderItemDao.setOrderItemRepository(orderItemRepository);
        orderItemDao.setOrderRepository(orderRepository);
    }

    @Test
    void setOrderItemRepository() {
        assertSame(orderItemDao.orderItemRepository, orderItemRepository);
    }

    @Test
    void setOrderRepository() {
        assertSame(orderItemDao.orderRepository, orderRepository);
    }

    @Test
    void getOrderItemById() {
        Integer id = 1;
        List<OrderItem> res = orderItemDao.getOrderItemById(id);
        Mockito.verify(orderItemRepository, times(1)).getOrderItemById(id);
    }
}