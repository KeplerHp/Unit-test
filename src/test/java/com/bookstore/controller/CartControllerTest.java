package com.bookstore.controller;

import com.bookstore.entity.CartItem;
import com.bookstore.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {
    @Mock CartService cartService;
    CartController cartController=new CartController();
    List<CartItem> test_cartitem_list=new ArrayList<CartItem>();

    @BeforeEach
    void initTest(){
        cartService= Mockito.mock(CartService.class);
        this.cartController.setCartService(cartService);
        lenient().when(cartService.getCartItems()).thenReturn(this.test_cartitem_list);
        lenient().doNothing().when(cartService).setCartItem(any(Integer.class),any(Boolean.class));
        lenient().when(cartService.getRealCartItems()).thenReturn(this.test_cartitem_list);
        lenient().doNothing().when(cartService).addCartItem(any(Integer.class),any(Integer.class),any(Boolean.class));
        lenient().doNothing().when(cartService).submitCart();
        lenient().doNothing().when(cartService).deleteCartItem(any(Integer.class));

    }


    @Test
    void getCartItems() {
        assertEquals(this.cartController.getCartItems(),this.test_cartitem_list);
    }

    @Test
    void setCartItem() {
        this.cartController.setCartItem(1,false);
    }

    @Test
    void getRealCartItems() {
        assertEquals(this.cartController.getRealCartItems(),this.test_cartitem_list);
    }

    @Test
    void addCartItem() {
        this.cartController.addCartItem(1,1,false);
    }

    @Test
    void submitCart() {
        this.cartController.submitCart();
    }

    @Test
    void deleteCartItem() {
        this.cartController.deleteCartItem(1);
    }
}