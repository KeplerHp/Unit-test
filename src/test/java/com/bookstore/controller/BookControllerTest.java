package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    BookService bookService;
    BookController bookController=new BookController();
    Book testbook=new Book();
    List<Book> testbook_list=new ArrayList<Book>();
    PageInfo<Book> testbook_page=new PageInfo<Book>();

    @BeforeEach
    void initTest(){
        bookService= Mockito.mock(BookService.class);
        this.bookController.setBookService(bookService);
        lenient().when(bookService.getBookByBookId(any(Integer.class))).thenReturn(testbook);
        lenient().when(bookService.getBookByName(any(String.class))).thenReturn(testbook_list);
        lenient().when(bookService.getBookByBookId(any(Integer.class))).thenReturn(testbook);
        lenient().doNothing().when(bookService).addBook(any(Map.class));
        lenient().doNothing().when(bookService).updateBook(any(Map.class));
        lenient().when(bookService.getBooks()).thenReturn(testbook_list);
        lenient().when(bookService.getBooksByPage(any(Integer.class))).thenReturn(testbook_page);
    }


    @Test
    void getBookById() {
        assertEquals(bookController.getBookById(1),testbook);
    }

    @Test
    void getBookByName() {
        assertEquals(bookController.getBookByName("test"),testbook_list);
    }

    @Test
    void deleteBookById() {
        bookController.deleteBookById(1);
    }

    @Test
    void addBook() {
        Map<String,String> map=new HashMap<String,String>() ;
        bookController.addBook(map);
    }

    @Test
    void updateBook() {
        Map<String,String> map=new HashMap<String,String>() ;
        bookController.updateBook(map);
    }

    @Test
    void getBooks() {
        assertEquals(bookController.getBooks(),testbook_list);
    }

    @Test
    void getBooksByPage() {
        assertEquals(bookController.getBooksByPage(1),testbook_page);
    }
}