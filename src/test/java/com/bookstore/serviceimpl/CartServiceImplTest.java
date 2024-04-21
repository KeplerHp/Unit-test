package com.bookstore.serviceimpl;

import com.bookstore.dao.CartDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {
    @Mock
    private CartDao mockCartDao;
    @InjectMocks
    private CartServiceImpl cartService;

    Integer bookId;
    Integer amount;
    boolean active;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        cartService.setCartDao(mockCartDao);
        bookId = 1;
        amount = 1;
        active = true;
    }

    @Test
    void setCartDao() {
        assertSame(mockCartDao, cartService.cartDao);
    }

    @Test
    void getCartItems() {
        cartService.getCartItems();
        Mockito.verify(mockCartDao, Mockito.times(1)).getCartItems();
    }

    @Test
    void getRealCartItems() {
        cartService.getRealCartItems();
        Mockito.verify(mockCartDao, Mockito.times(1)).getRealCartItems();
    }

    @Test
    void addCartItem() {
        cartService.addCartItem(bookId, amount, active);
        Mockito.verify(mockCartDao, Mockito.times(1)).addCartItem(bookId, amount, active);
    }

    @Test
    void setCartItem() {
        cartService.setCartItem(bookId, active);
        Mockito.verify(mockCartDao, Mockito.times(1)).setCartItem(bookId, active);
    }

    @Test
    void deleteCartItem() {
        cartService.deleteCartItem(bookId);
        Mockito.verify(mockCartDao, Mockito.times(1)).deleteCartItem(bookId);
    }

    @Test
    void submitCart() {
        cartService.submitCart();
        Mockito.verify(mockCartDao, Mockito.times(1)).submitCart();
    }
}