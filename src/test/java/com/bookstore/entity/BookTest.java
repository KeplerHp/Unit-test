package com.bookstore.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    Book testBook = new Book();
    @BeforeEach
    void setUp() {
        testBook.setBookId(1);
        testBook.setName("Book Title");
        testBook.setAuthor("Author Name");
        testBook.setPrice(new BigDecimal("19.99"));
        testBook.setIsbn("978-3-16-148410-0");
        testBook.setInventory(10);
        testBook.setDescription("This is a book description.");
        testBook.setImage("book.jpg");
        testBook.setType("Fiction");
        testBook.setBrief("Brief summary of the book.");
        testBook.setEnabled(true);
    }

    @Test
    void getBookId() {
        assertEquals(1, testBook.getBookId());
    }

    @Test
    void getName() {
        assertEquals("Book Title", testBook.getName());
    }

    @Test
    void getAuthor() {
        assertEquals("Author Name", testBook.getAuthor());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal("19.99"), testBook.getPrice());
    }

    @Test
    void getIsbn() {
        assertEquals("978-3-16-148410-0", testBook.getIsbn());
    }

    @Test
    void getInventory() {
        assertEquals(10, testBook.getInventory());
    }

    @Test
    void getDescription() {
        assertEquals("This is a book description.", testBook.getDescription());
    }

    @Test
    void getImage() {
        assertEquals("book.jpg", testBook.getImage());
    }

    @Test
    void getType() {
        assertEquals("Fiction", testBook.getType());
    }

    @Test
    void getBrief() {
        assertEquals("Brief summary of the book.", testBook.getBrief());
    }

    @Test
    void getEnabled() {
        assertEquals(true, testBook.getEnabled());
    }

}