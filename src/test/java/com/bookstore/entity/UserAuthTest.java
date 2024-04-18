package com.bookstore.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthTest {
    UserAuth userAuth;
    @BeforeEach
    void setUp() {
        userAuth = new UserAuth();
        userAuth.setUserId(1);
        userAuth.setUsername("johnDoe");
        userAuth.setUserPassword("password123");
        userAuth.setUserType(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserId() {
        assertEquals(1, userAuth.getUserId());
    }

    @Test
    void getUsername() {
        assertEquals("johnDoe", userAuth.getUsername());
    }

    @Test
    void getUserPassword() {
        assertEquals("password123", userAuth.getUserPassword());
    }

    @Test
    void getUserType() {
        assertEquals(1, userAuth.getUserType());
    }
}