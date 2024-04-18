package com.bookstore.daoimpl;

import com.bookstore.entity.User;
import com.bookstore.entity.UserAuth;
import com.bookstore.repository.UserAuthRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import com.bookstore.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserDaoImpl userDao;
    private UserAuthRepository mockUserAuthRepository;
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
        mockUserAuthRepository = Mockito.mock(UserAuthRepository.class);
        mockUserRepository = Mockito.mock(UserRepository.class);
        userDao.setUserAuthRepository(mockUserAuthRepository);
        userDao.setUserRepository(mockUserRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setUserRepository() {
        assertEquals(mockUserRepository, userDao.userRepository);
    }

    @Test
    void setUserAuthRepository() {
        assertEquals(mockUserAuthRepository, userDao.userAuthRepository);
    }

    @Test
    void checkAuth() {
        UserAuth expectedUserAuth = new UserAuth();
        Mockito.when(mockUserAuthRepository.checkAuth("admin", "admin"))
                .thenReturn(expectedUserAuth);
        UserAuth result = userDao.checkAuth("admin", "admin");
        assertEquals(expectedUserAuth, result);
    }

    @Test
    void getUser_Fail() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(null);

        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        User result = userDao.getUser();

        assertNull(result);
    }


    @Test
    void getUser_Success() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", 1);
        request.setSession(session);

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john@example.com");
        Mockito.when(mockUserRepository.getUserById(1))
                .thenReturn(expectedUser);

        User result = userDao.getUser();

        assertEquals(expectedUser, result);
    }

    @Test
    void getUserById() {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john@example.com");
        Mockito.when(mockUserRepository.getUserById(1))
                .thenReturn(expectedUser);

        User result = userDao.getUserById(1);

        assertEquals(expectedUser, result);
    }

    @Test
    void register_Success() {
        String username = "john123";
        Mockito.when(mockUserAuthRepository.getUserAuthByUsername(username)).thenReturn(null);

        String name = "John Doe";
        String email = "john@example.com";
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        Mockito.when(mockUserRepository.getUserByName(name)).thenReturn(newUser);

        String password = "password123";
        Mockito.doNothing().when(mockUserAuthRepository).addUserAuth(newUser.getUserId(), username, password, 0);

        boolean result = userDao.register(username, password, name, email);

        assertTrue(result);
    }

    @Test
    void register_Fail() {
        String username = "john123";
        UserAuth existingUserAuth = new UserAuth();
        Mockito.when(mockUserAuthRepository.getUserAuthByUsername(username)).thenReturn(existingUserAuth);

        boolean result = userDao.register(username, "password123", "John Doe", "john@example.com");

        assertFalse(result);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        Mockito.when(mockUserRepository.getAllUsers())
                .thenReturn(users);

        List<User> result = userDao.getAllUsers();

        assertEquals(users, result);
    }

    @Test
    void updateUserStatus() {
        Integer userId = 1;
        Boolean enabled = true;
        userDao.updateUserStatus(userId, enabled);

        Mockito.verify(mockUserRepository).updateUserStatus(userId, enabled);
    }

    @Test
    void registerCheck() {
        String username = "admin";

        Mockito.when(mockUserAuthRepository.registerCheck(username)).thenReturn(1);

        boolean result = userDao.registerCheck(username);

        assertTrue(result);
    }

    @Test
    void registerCheckRes() {
        String username = "admin";

        Mockito.when(mockUserAuthRepository.registerCheck(username)).thenReturn(-1);

        boolean result = userDao.registerCheck(username);

        assertFalse(result);
    }
}