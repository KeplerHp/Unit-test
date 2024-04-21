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
import static org.mockito.Mockito.*;

class OrderDaoImplTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    BookRepository bookRepository;

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
        // 模拟BookRepository
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setTime(new Timestamp(1618970400000L)); // 2021-04-21 00:00:00
        order1.setPrice(new BigDecimal(20));
        List<OrderItem> items1 = new ArrayList<>();
        OrderItem item1 = new OrderItem();
        item1.setBookId(1);
        item1.setAmount(2);
        items1.add(item1);
        // Adding the same item again to simulate duplicate order items
        items1.add(item1);
        order1.setOrderItem(items1);
        orders.add(order1);

        Order order2 = new Order();
        order2.setTime(new Timestamp(1619056800000L)); // 2021-04-22 00:00:00
        order2.setPrice(new BigDecimal(30));
        List<OrderItem> items2 = new ArrayList<>();
        OrderItem item2 = new OrderItem();
        item2.setBookId(2);
        item2.setAmount(3);
        items2.add(item2);
        order2.setOrderItem(items2);
        orders.add(order2);

        Book book1 = new Book();
        book1.setBookId(1);
        book1.setName("Book1");
        book1.setPrice(new BigDecimal(10));
        Book book2 = new Book();
        book2.setBookId(2);
        book2.setName("Book2");
        book2.setPrice(new BigDecimal(15));

        when(orderRepository.getAllOrderByTime(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
        when(bookRepository.getBookByBookId(1)).thenReturn(book1);
        when(bookRepository.getBookByBookId(2)).thenReturn(book2);

        // Invoke method under test
        List<OrderStat> result = orderDao.getAllOrderByTime("1618970400000", "1619056800000");

        // Assertions
        assertEquals(3, result.size()); // Asserting the size of the result list
        OrderStat orderStat1 = result.get(0);
        assertEquals(1, orderStat1.getBookId());
        assertEquals("Book1", orderStat1.getName());
        assertEquals(4, orderStat1.getAmount());
        assertEquals(new BigDecimal(10), orderStat1.getPrice());

        OrderStat orderStat2 = result.get(1);
        assertEquals(2, orderStat2.getBookId());
        assertEquals("Book2", orderStat2.getName());
        assertEquals(3, orderStat2.getAmount());
        assertEquals(new BigDecimal(15), orderStat2.getPrice());
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
            when(orderRepository.getOrderByTime(id, new Timestamp(Long.parseLong(time_start)), new Timestamp(Long.parseLong(time_end)))).thenReturn(myOrder);
            when(bookRepository.getBookByBookId(ArgumentMatchers.any())).thenReturn(book);

            List<OrderStat> res = orderDao.getOrderByTime(time_start, time_end);
            theMock.verify(() -> SessionUtil.getUserId());
            Mockito.verify(orderRepository, times(1)).getOrderByTime(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
//            // bookRepository.getBookByBookId(myItem.getBookId())调用4次，2*2
            Mockito.verify(bookRepository, times(2)).getBookByBookId(ArgumentMatchers.any());
            assertEquals(2+1, res.size());
        }
    }
}