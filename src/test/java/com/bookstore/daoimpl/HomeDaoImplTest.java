package com.bookstore.daoimpl;

import com.bookstore.repository.HomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class HomeDaoImplTest {

    @Mock
    HomeRepository homeRepository;

    @InjectMocks
    HomeDaoImpl homeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        homeDao.setHomepageRepository(homeRepository);
    }

    @Test
    void setHomepageRepository() {
        assertEquals(homeRepository, homeDao.homeRepository);
    }

    @Test
    void getHomeContent() {
        homeDao.getHomeContent();
        Mockito.verify(homeRepository, Mockito.times(2)).getHomeContent();
    }
}