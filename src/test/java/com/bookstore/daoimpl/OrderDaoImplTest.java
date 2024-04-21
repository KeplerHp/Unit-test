package com.bookstore.daoimpl;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.OrderStat;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;

class OrderDaoImplTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    SessionUtil sessionUtil;

    @InjectMocks
    OrderDaoImpl orderDao;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        orderDao.setOrderRepository(orderRepository);
        orderDao.setBookRepository(bookRepository);
    }

    @Test
    void setOrderRepository() {
        assertSame(orderDao.orderRepository, orderRepository);
    }

    @Test
    void setBookRepository() {
        assertSame(orderDao.bookRepository, bookRepository);
    }

    @Test
    void getOrder() {
        try(MockedStatic<SessionUtil> theMock = Mockito.mockStatic(SessionUtil.class)){
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            List<Order> res = orderDao.getOrder();
            theMock.verify(() -> SessionUtil.getUserId());
            assertNull(res);
        }
        try(MockedStatic<SessionUtil> theMock = Mockito.mockStatic(SessionUtil.class)){
            Integer id = 1;
            theMock.when(SessionUtil::getUserId).thenReturn(id);
            orderDao.getOrder();
            theMock.verify(() -> SessionUtil.getUserId());
            Mockito.verify(orderRepository, times(1)).getOrderByUserId(id);
        }
    }

    @Test
    void getOrderByOrderId() {
        Integer id = 1;
        orderDao.getOrderByOrderId(id);
        Mockito.verify(orderRepository, times(1)).getOrderByOrderId(id);
    }

    @Test
    void getAllOrder() {
        orderDao.getAllOrder();
        Mockito.verify(orderRepository, times(1)).getAllOrder();
    }

    @Test
    void getAllOrderByTime() {
        Book book = new Book();
        book.setName("test");
        book.setPrice(new BigDecimal("19.99"));

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderId(1);
        orderItem1.setAmount(1);
        orderItem1.setBook(book);
        orderItem1.setBookId(1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderId(2);
        orderItem2.setAmount(2);
        orderItem2.setBook(book);
        orderItem2.setBookId(2);

        List<OrderItem> myOrderItem1 = new ArrayList<>();
        myOrderItem1.add(orderItem1);
        List<OrderItem> myOrderItem2 = new ArrayList<>();
        myOrderItem2.add(orderItem1);
        myOrderItem2.add(orderItem2);

        Order order1 = new Order();
        order1.setTime(new Timestamp(10000));
        order1.setOrderItem(myOrderItem1);
        order1.setPrice(new BigDecimal("19.99"));
        Order order2 = new Order();
        order2.setTime(new Timestamp(20000));
        order2.setOrderItem(myOrderItem2);
        order2.setPrice(new BigDecimal("59.97"));

        List<Order> myOrder = new ArrayList<>();
        myOrder.add(order1);
        myOrder.add(order2);

        String time_start = "0";
        String time_end = "30000";
        Mockito.when(orderRepository.getAllOrderByTime(new Timestamp(Long.parseLong(time_start)), new Timestamp(Long.parseLong(time_end)))).thenReturn(myOrder);
        Mockito.when(bookRepository.getBookByBookId(ArgumentMatchers.any())).thenReturn(book);

        List<OrderStat> res = orderDao.getAllOrderByTime(time_start, time_end);
        Mockito.verify(orderRepository, times(1)).getAllOrderByTime(ArgumentMatchers.any(), ArgumentMatchers.any());
        // bookRepository.getBookByBookId(myItem.getBookId())调用4次，2*2
        Mockito.verify(bookRepository, times(2)).getBookByBookId(ArgumentMatchers.any());
        assertEquals(2+1, res.size());
    }

    @Test
    void getOrderByTime() {
        String time_start = "0";
        String time_end = "30000";
        try(MockedStatic<SessionUtil> theMock = Mockito.mockStatic(SessionUtil.class)){
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            List<OrderStat> res = orderDao.getOrderByTime(time_start, time_end);
            theMock.verify(() -> SessionUtil.getUserId());
            assertNull(res);
        }
        try(MockedStatic<SessionUtil> theMock = Mockito.mockStatic(SessionUtil.class)){
            Integer id = 1;

            Book book = new Book();
            book.setName("test");
            book.setPrice(new BigDecimal("19.99"));

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrderId(1);
            orderItem1.setAmount(1);
            orderItem1.setBook(book);
            orderItem1.setBookId(1);
            OrderItem orderItem2 = new OrderItem();
            orderItem2.setOrderId(2);
            orderItem2.setAmount(2);
            orderItem2.setBook(book);
            orderItem2.setBookId(2);

            List<OrderItem> myOrderItem1 = new ArrayList<>();
            myOrderItem1.add(orderItem1);
            List<OrderItem> myOrderItem2 = new ArrayList<>();
            myOrderItem2.add(orderItem1);
            myOrderItem2.add(orderItem2);

            Order order1 = new Order();
            order1.setTime(new Timestamp(10000));
            order1.setOrderItem(myOrderItem1);
            order1.setPrice(new BigDecimal("19.99"));
            Order order2 = new Order();
            order2.setTime(new Timestamp(20000));
            order2.setOrderItem(myOrderItem2);
            order2.setPrice(new BigDecimal("59.97"));

            List<Order> myOrder = new ArrayList<>();
            myOrder.add(order1);
            myOrder.add(order2);

            theMock.when(SessionUtil::getUserId).thenReturn(id);
            Mockito.when(orderRepository.getOrderByTime(id, new Timestamp(Long.parseLong(time_start)), new Timestamp(Long.parseLong(time_end)))).thenReturn(myOrder);
            Mockito.when(bookRepository.getBookByBookId(ArgumentMatchers.any())).thenReturn(book);

            List<OrderStat> res = orderDao.getOrderByTime(time_start, time_end);
            theMock.verify(() -> SessionUtil.getUserId());
            Mockito.verify(orderRepository, times(1)).getOrderByTime(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
//            // bookRepository.getBookByBookId(myItem.getBookId())调用4次，2*2
            Mockito.verify(bookRepository, times(2)).getBookByBookId(ArgumentMatchers.any());
            assertEquals(2+1, res.size());
        }
    }
}