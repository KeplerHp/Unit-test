package com.bookstore.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setEnabled(true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserId() {
        assertEquals(1, user.getUserId());
    }

    @Test
    void getName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void getEnabled() {
        assertTrue(user.getEnabled());
    }

}