package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        // id 字段自增，因此每个测试的id都不同，但必定只有一本书
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
        bookRepository.save(testBook);
    }

    @AfterEach
    public void tearDown() {
        // 在每个测试方法之后删除测试数据
        bookRepository.deleteAll();
    }

    @Test
    void getBooks() {
        List<Book> foundBook = bookRepository.getBooks();
        assertNotNull(foundBook);
        assertEquals(testBook, foundBook.get(0));
    }

    @Test
    void getBookByBookId() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        Book foundBook = bookRepository.getBookByBookId(id);
        assertNotNull(foundBook);
        assertEquals(testBook, foundBook);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//修改操作需要禁用默认的事务管理使得修改生效
    void modifyInventory() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyInventory(id, 114514);
        Book foundBook = bookRepository.getBookByBookId(id);
        assertNotNull(foundBook);
        assertEquals(114514, foundBook.getInventory());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//修改操作需要禁用默认的事务管理使得修改生效
    void deleteBookByBookId() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.deleteBookByBookId(id);
        assertEquals(0, bookRepository.getBooks().size());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//修改操作需要禁用默认的事务管理使得修改生效
    void addBook() {
        bookRepository.addBook("test",null,null,null,null,null,null,null,null);
        assertEquals("test", bookRepository.getBookByName("test").get(0).getName());
        assertEquals(2, bookRepository.getBooks().size());
    }

    @Test
    void getBookByName() {
        assertEquals("Book Title", bookRepository.getBookByName("Book Title").get(0).getName());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//修改操作需要禁用默认的事务管理使得修改生效
    void modifyName() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyName(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getName());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyAuthor() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyAuthor(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getAuthor());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyPrice() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyPrice(id, new BigDecimal("1145.14"));
        assertEquals(new BigDecimal("1145.14"), bookRepository.getBookByBookId(id).getPrice());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyISBN() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyISBN(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getIsbn());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyDescription() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyDescription(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getDescription());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyImage() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyImage(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getImage());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyType() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyType(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getType());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void modifyBrief() {
        Integer id = bookRepository.getBooks().get(0).getBookId();
        bookRepository.modifyBrief(id, "test");
        assertEquals("test", bookRepository.getBookByBookId(id).getBrief());
    }
}