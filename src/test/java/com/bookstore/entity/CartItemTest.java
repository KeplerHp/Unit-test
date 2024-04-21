package com.bookstore.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CartItemTest {
    CartItem cartItem = new CartItem();
    Book book = new Book();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetterAndSetter()
    {
        book.setBookId(1);
        book.setName("test");
        cartItem.setBook(book);
        cartItem.setUserId(1);
        cartItem.setBookId(1);
        cartItem.setAmount(1);
        cartItem.setActive(true);

        assertEquals(1, cartItem.getUserId());
        assertEquals(1, cartItem.getBookId());
        assertEquals(1, cartItem.getAmount());
        assertEquals(true, cartItem.getActive());
        assertEquals(book, cartItem.getBook());
        assertEquals(1, cartItem.getBookId());
    }
}
class primaryKeyCartItemTest {
    PrimaryKey_CartItem primaryKey = new PrimaryKey_CartItem();

    @Test
    void getUserId() {
        primaryKey.setUserId(1);
        assertEquals(1, primaryKey.getUserId());
    }

    @Test
    void getBookId() {
        primaryKey.setBookId(1);
        assertEquals(1, primaryKey.getBookId());
    }
}