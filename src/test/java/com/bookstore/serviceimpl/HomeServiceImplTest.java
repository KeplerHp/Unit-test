package com.bookstore.serviceimpl;

import com.bookstore.dao.HomeDao;
import com.bookstore.service.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class HomeServiceImplTest {
    @Mock
    HomeDao homeDao;
    @InjectMocks
    HomeServiceImpl homeService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        homeService.setHomepageDao(homeDao);
    }

    @Test
    void setHomepageDao() {
        assertEquals(homeDao, homeService.homeDao);
    }

    @Test
    void getHomeContent() {
        homeService.getHomeContent();
        Mockito.verify(homeDao, Mockito.times(1)).getHomeContent();
    }
}