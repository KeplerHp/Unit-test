package com.bookstore;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mockStatic;


@SpringBootTest
class BookstoreApplicationTests {
    @Test
    void main_startsSpringApplication() {
        try(MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
            BookstoreApplication.main(new String[0]);
            mockedSpringApplication.verify(() -> SpringApplication.run(BookstoreApplication.class, new String[0]));
        }
    }
    @Test
    void contextLoads() {
    }

}
