package com.bookstore.daoimpl;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookDaoImpl bookDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookDao.setBookRepository(bookRepository);
    }

    @Test
    void setBookRepository() {
        assertSame(bookDao.bookRepository, bookRepository);
    }

    @Test
    void getBooks() {
        bookDao.getBooks();
        Mockito.verify(bookRepository, Mockito.times(1)).getBooks();
    }

    @Test
    void getBookByBookId() {
        Integer id = 1;
        bookDao.getBookByBookId(id);
        Mockito.verify(bookRepository, Mockito.times(1)).getBookByBookId(id);
    }

    @Test
    void deleteBookByBookId() {
        Integer id = 1;
        bookDao.deleteBookByBookId(id);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteBookByBookId(id);
    }

    @Test
    void addBook() {
        Map<String, String> params = Mockito.mock(Map.class);

        Mockito.when(params.get("name")).thenReturn("Book Name");
        Mockito.when(params.get("author")).thenReturn("Author Name");
        Mockito.when(params.get("price")).thenReturn("19.99");
        Mockito.when(params.get("isbn")).thenReturn("978-3-16-148410-0");
        Mockito.when(params.get("inventory")).thenReturn("10");
        Mockito.when(params.get("description")).thenReturn("Book Description");
        Mockito.when(params.get("image")).thenReturn("book.jpg");
        Mockito.when(params.get("type")).thenReturn("Fiction");
        Mockito.when(params.get("brief")).thenReturn("Brief summary of the book");

        bookDao.addBook(params);

        Mockito.verify(bookRepository, Mockito.times(1)).addBook(
                "Book Name", "Author Name", new BigDecimal("19.99"),
                "978-3-16-148410-0", 10, "Book Description",
                "book.jpg", "Fiction", "Brief summary of the book"
        );
    }

    @Test
    void getBookByName() {
        String name = "test";
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setName(name);
        books.add(book);
        Mockito.when(bookDao.getBookByName(name)).thenReturn(books);
        bookDao.getBookByName(name);
        Mockito.verify(bookRepository, Mockito.times(1)).getBookByName("%"+name+"%");
        assertSame("test", bookDao.getBookByName(name).get(0).getName());
    }

    @Test
    void updateBook() {
        Map<String, String> params = Mockito.mock(Map.class);

        Mockito.when(params.get("bookId")).thenReturn("1");
        Mockito.when(params.get("name")).thenReturn("Book Name");
        Mockito.when(params.get("author")).thenReturn("Author Name");
        Mockito.when(params.get("price")).thenReturn("19.99");
        Mockito.when(params.get("isbn")).thenReturn("978-3-16-148410-0");
        Mockito.when(params.get("inventory")).thenReturn("10");
        Mockito.when(params.get("description")).thenReturn("Book Description");
        Mockito.when(params.get("image")).thenReturn("book.jpg");
        Mockito.when(params.get("type")).thenReturn("Fiction");
        Mockito.when(params.get("brief")).thenReturn("Brief summary of the book");

        BigDecimal price = new BigDecimal(params.get("price"));
        price = price.setScale(2, RoundingMode.HALF_UP);

        bookDao.updateBook(params);

        Mockito.verify(bookRepository, Mockito.times(1)).modifyName(1, "Book Name");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyAuthor(1, "Author Name");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyISBN(1, "978-3-16-148410-0");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyInventory(1, 10);
        Mockito.verify(bookRepository, Mockito.times(1)).modifyDescription(1, "Book Description");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyImage(1, "book.jpg");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyType(1, "Fiction");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyBrief(1, "Brief summary of the book");
        Mockito.verify(bookRepository, Mockito.times(1)).modifyPrice(1, price);
    }

    @Test
    void getBooksByPage() {
        Integer num = 1;
        Book book = new Book();
        book.setName("test");
        book.setBookId(1);
        List<Book> books = new ArrayList<>();
        books.add(book);
        Mockito.when(bookRepository.getBooks()).thenReturn(books);

        PageInfo<Book> pageInfo = bookDao.getBooksByPage(num);

        Mockito.verify(bookRepository, Mockito.times(2)).getBooks();
        assertSame(books.get(0), pageInfo.getList().get(0));
        Long num_of_page = 1L;
        assertSame(num_of_page, pageInfo.getTotal());
    }
}