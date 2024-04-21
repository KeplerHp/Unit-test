package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.entity.UserAuth;
import com.bookstore.service.UserService;
import com.bookstore.utils.messageUtils.Message;
import com.bookstore.utils.messageUtils.MessageUtil;
import com.bookstore.utils.sessionUtils.SessionUtil;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    UserController userController;
    UserAuth userAuth = new UserAuth();
    User user = new User();
    List<User> user_list = new ArrayList<User>();

    @BeforeEach
    void initTest() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void checkAuth() {
        lenient().when(userService.checkAuth(any(String.class), any(String.class))).thenReturn(userAuth);
        assertEquals(userController.checkAuth("", ""), userAuth);
    }

    @Test
    void getUser() {
        lenient().when(userService.getUser()).thenReturn(user);
        assertEquals(userController.getUser(), user);
    }

    @Test
    void getUserById() {
        lenient().when(userService.getUserById(any(Integer.class))).thenReturn(user);
        assertEquals(userController.getUserById(1), user);
    }

    @Test
    void getAllUsers() {
        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            theMock.when(SessionUtil::getAuth).thenReturn(null);
            assertEquals(userController.getAllUsers(), null);
        }

        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            JSONObject tmp = new JSONObject();
            tmp.put("userType", 0);
            theMock.when(SessionUtil::getAuth).thenReturn(tmp);
            assertEquals(userController.getAllUsers(), null);
        }

        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            JSONObject tmp = new JSONObject();
            tmp.put("userType", 1);
            theMock.when(SessionUtil::getAuth).thenReturn(tmp);
            lenient().when(userService.getAllUsers()).thenReturn(user_list);
            assertEquals(userController.getAllUsers(), user_list);
        }

    }

    @Test
    void updateUserStatus() {
        lenient().doNothing().when(userService).updateUserStatus(any(Integer.class), any(Boolean.class));
        userController.updateUserStatus(1, true);
    }

    @Test
    void register() {
        String username = "USERNAME", password = "PASSWORD", email = "EMAIL";
        String nameT = "NAMET", nameF = "NAMEF";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("email", email);
        Message t, f;

        lenient().when(userService.register(username, password, nameT, email)).thenReturn(true);
        map.put("name", nameT);
        assertEquals(userController.register(map).getMessage(), MessageUtil.createMessage(MessageUtil.REGISTER_SUCCESS_CODE, MessageUtil.REGISTER_SUCCESS_MSG).getMessage());

        lenient().when(userService.register(username, password, nameF, email)).thenReturn(false);
        map.put("name", nameF);
        assertEquals(userController.register(map).getMessage(), MessageUtil.createMessage(MessageUtil.REGISTER_ERROR_CODE, MessageUtil.REGISTER_ERROR_MSG).getMessage());
    }

    @Test
    void registerCheck() {
        lenient().when(userService.registerCheck(any(String.class))).thenReturn(false);
        assertEquals(userController.registerCheck(""), false);
    }

    @Test
    void checkSession() {
        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            theMock.when(SessionUtil::getAuth).thenReturn(null);
            assertEquals(userController.checkSession().getMessage(), MessageUtil.createMessage(MessageUtil.NOT_LOGIN_CODE, MessageUtil.NOT_LOGIN_MSG).getMessage());
        }

        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            JSONObject auth = new JSONObject();
            auth.put("userType", 0);
            theMock.when(SessionUtil::getAuth).thenReturn(auth);
            assertEquals(userController.checkSession().getMessage(), MessageUtil
                    .createMessage(MessageUtil.NOT_ALLOW_CODE, MessageUtil.NOT_ALLOW_MSG).getMessage());
        }

        try (MockedStatic<SessionUtil> theMock = mockStatic(SessionUtil.class)) {
            JSONObject auth = new JSONObject();
            auth.put("userType", 1);
            theMock.when(SessionUtil::getAuth).thenReturn(auth);
            assertEquals(userController.checkSession().getMessage(), MessageUtil
                    .createMessage(MessageUtil.ALREADY_LOGIN_CODE, MessageUtil.ALREADY_LOGIN_MSG).getMessage());
        }
    }
}