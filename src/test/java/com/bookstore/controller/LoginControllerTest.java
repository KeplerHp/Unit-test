package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.entity.UserAuth;
import com.bookstore.service.UserService;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
//import org.mockito.MockedStatic;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.mockStatic;


@ExtendWith(SpringExtension.class)
class LoginControllerTest {

    @Mock
    UserService userService;

    LoginController loginController;
    UserAuth enable_userAuth;
    UserAuth unable_userAuth;
    User enable_user;
    User unable_user;


    @BeforeEach
    public void initTest() {
        userService=Mockito.mock(UserService.class);
        System.out.println("HELLO");
        this.enable_userAuth=new UserAuth();
        this.unable_userAuth=new UserAuth();
        this.enable_user=new User();
        this.unable_user=new User();
        this.loginController = new LoginController(userService);
        this.enable_userAuth.setUserId(1);
        this.unable_userAuth.setUserId(2);
        this.enable_user.setEnabled(true);
        this.unable_user.setEnabled(false);
        lenient().when(userService.checkAuth("enable", "enable")).thenReturn(this.enable_userAuth);
        lenient().when(userService.checkAuth("unable", "unable")).thenReturn(this.unable_userAuth);
        lenient().when(userService.checkAuth("invalid", "invalid")).thenReturn(null);

        lenient().when(userService.getUserById(1)).thenReturn(this.enable_user);
        lenient().when(userService.getUserById(2)).thenReturn(this.unable_user);
    }


    @Test
    void login_enable() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "enable");
        map.put("userPassword", "enable");
        this.loginController.login(map);
    }

    @Test
    void login_unable() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "unable");
        map.put("userPassword", "unable");
        this.loginController.login(map);
    }

    @Test
    void login_invalid() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "invalid");
        map.put("userPassword", "invalid");
        this.loginController.login(map);
    }

    @Test
    void logout_true(){
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("Mock Static Success!");
            theMock.when(SessionUtil::removeSession).thenReturn(true);
            this.loginController.logout();
        }
    }

    @Test
    void logout_false(){
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("Mock Static Success!");
            theMock.when(SessionUtil::removeSession).thenReturn(false);
            this.loginController.logout();
        }
    }
}