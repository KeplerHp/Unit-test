package com.bookstore.controller;

import com.bookstore.entity.HomeItem;
import com.bookstore.service.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock HomeService homeService;
    HomeController homeController=new HomeController();
    List<HomeItem> test_homeitem_list=new ArrayList<HomeItem>();

    @BeforeEach
    void initTest(){
        homeService= Mockito.mock(HomeService.class);
        this.homeController.setHomepageService(homeService);
        lenient().when(homeService.getHomeContent()).thenReturn(this.test_homeitem_list);
    }

    @Test
    void getHomeContent() {
        assertEquals(this.homeController.getHomeContent(),this.test_homeitem_list);
    }
}