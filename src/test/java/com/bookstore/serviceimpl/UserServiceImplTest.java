package com.bookstore.serviceimpl;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.User;
import com.bookstore.entity.UserAuth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userService;
    UserDao mockUserDao;
    @BeforeEach
    void setUp() {
        mockUserDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImpl(mockUserDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkAuth() {
        String username = "johnDoe";
        String password = "password123";
        UserAuth expectedUserAuth = new UserAuth();
        expectedUserAuth.setUserId(1);
        expectedUserAuth.setUsername(username);
        expectedUserAuth.setUserPassword(password);
        Mockito.when(mockUserDao.checkAuth(username, password)).thenReturn(expectedUserAuth);

        UserAuth result = userService.checkAuth(username, password);

        Mockito.verify(mockUserDao, Mockito.times(1)).checkAuth(username, password);
        assertEquals(expectedUserAuth, result);
    }

    @Test
    void getUser() {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john@example.com");
        Mockito.when(mockUserDao.getUser()).thenReturn(expectedUser);

        User result = userService.getUser();

        Mockito.verify(mockUserDao, Mockito.times(1)).getUser();
        assertEquals(expectedUser, result);
    }

    @Test
    void getUserById() {
        Integer userId = 1;
        User expectedUser = new User();
        expectedUser.setUserId(userId);
        Mockito.when(mockUserDao.getUserById(userId)).thenReturn(expectedUser);

        User result = userService.getUserById(userId);

        Mockito.verify(mockUserDao, Mockito.times(1)).getUserById(userId);
        assertEquals(expectedUser, result);
    }

    @Test
    void register() {
        String username = "johnDoe";
        String password = "password123";
        String name = "John Doe";
        String email = "john@example.com";
        Mockito.when(mockUserDao.register(username, password, name, email)).thenReturn(true);

        boolean result = userService.register(username, password, name, email);

        Mockito.verify(mockUserDao, Mockito.times(1)).register(username, password, name, email);
        assertTrue(result);
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setUserId(1);
        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        user2.setUserId(2);
        List<User> expectedUsers = Arrays.asList(user1, user2);
        Mockito.when(mockUserDao.getAllUsers()).thenReturn(expectedUsers);

        List<User> result = userService.getAllUsers();

        Mockito.verify(mockUserDao, Mockito.times(1)).getAllUsers();
        assertEquals(expectedUsers, result);
    }

    @Test
    void updateUserStatus() {
        int userId = 1;
        boolean newStatus = true;
        userService.updateUserStatus(userId, newStatus);
        Mockito.verify(mockUserDao, Mockito.times(1)).updateUserStatus(userId, newStatus);
    }

    @Test
    void registerCheck() {
        String username = "john_doe";
        boolean expectedResult = true;
        Mockito.when(mockUserDao.registerCheck(username)).thenReturn(expectedResult);
        boolean result = userService.registerCheck(username);

        Mockito.verify(mockUserDao, Mockito.times(1)).registerCheck(username);
        assertEquals(expectedResult, result);
    }
}