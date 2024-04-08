package com.bookstore.serviceimpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.bookstore.dao.BookDao;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    @Mock
    private BookDao mockBookDao;
    @InjectMocks
    private BookServiceImpl bookService;
    Map<String, String> params;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService.setBookDao(mockBookDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setBookDao() {
        assertSame(mockBookDao, bookService.bookDao);
    }

    @Test
    void getBooks() {
        bookService.getBooks();
        Mockito.verify(mockBookDao,times(1)).getBooks();
    }

    @Test
    void getBookByBookId() {
        Integer id = 1;
        bookService.getBookByBookId(id);
        Mockito.verify(mockBookDao,times(1)).getBookByBookId(id);
    }

    @Test
    void deleteBookByBookId() {
        Integer id = 1;
        bookService.deleteBookByBookId(id);
        Mockito.verify(mockBookDao,times(1)).deleteBookByBookId(id);
    }

    @Test
    void addBook() {
        bookService.addBook(params);
        Mockito.verify(mockBookDao,times(1)).addBook(params);
    }

    @Test
    void getBookByName() {
        String name = "test";
        bookService.getBookByName(name);
        Mockito.verify(mockBookDao,times(1)).getBookByName(name);
    }

    @Test
    void updateBook() {
        bookService.updateBook(params);
        Mockito.verify(mockBookDao,times(1)).updateBook(params);
    }

    @Test
    void getBooksByPage() {
        Integer num = 1;
        bookService.getBooksByPage(num);
        Mockito.verify(mockBookDao,times(1)).getBooksByPage(num);
    }
}