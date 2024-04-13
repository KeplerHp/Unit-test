package com.bookstore.daoimpl;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class CartDaoImplTest {
    @Mock
    CartRepository cartRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    SessionUtil sessionUtil;

    @InjectMocks
    CartDaoImpl cartDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartDao.setCartRepository(cartRepository);
        cartDao.setBookRepository(bookRepository);
        cartDao.setOrderRepository(orderRepository);
        cartDao.setOrderItemRepository(orderItemRepository);
    }

    @Test
    void setCartRepository() {
        assertSame(cartRepository, cartDao.cartRepository);
    }

    @Test
    void setBookRepository() {
        assertSame(bookRepository, cartDao.bookRepository);
    }

    @Test
    void setOrderRepository() {
        assertSame(orderRepository, cartDao.orderRepository);
    }

    @Test
    void setOrderItemRepository() {
        assertSame(orderItemRepository, cartDao.orderItemRepository);
    }

    @Test
    void getCartItems() {
        cartDao.getCartItems();
        // Mockito.verify(sessionUtil, times(1));
    }

    @Test
    void getRealCartItems() {
    }

    @Test
    void addCartItem() {
    }

    @Test
    void setCartItem() {
    }

    @Test
    void deleteCartItem() {
    }

    @Test
    void submitCart() {
    }
}