package com.bookstore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeItemTest {

    HomeItem homeItem = new HomeItem();
    @Test
    void getImageId() {
        homeItem.setImageId(1);
        assertEquals(1, homeItem.getImageId());
    }

    @Test
    void getImage() {
        homeItem.setImage("image.jpg");
        assertEquals("image.jpg", homeItem.getImage());
    }

}