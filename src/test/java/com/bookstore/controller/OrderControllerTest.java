package com.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderStat;
import com.bookstore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    OrderController orderController=new OrderController();
    List<OrderStat> test_orderstat_list=new ArrayList<OrderStat>();
    Order test_order=new Order();
    List<Order> test_order_list=new ArrayList<Order>();

    @BeforeEach
    void initTest(){
        Order tmp=new Order();
        this.test_order_list.add(tmp);
        this.test_order_list.add(tmp);
        this.orderService= Mockito.mock(OrderService.class);
        this.orderController.setOrderService(this.orderService);
        lenient().when(this.orderService.getOrderByTime(any(String.class),any(String.class))).thenReturn(this.test_orderstat_list);
        lenient().when(this.orderService.getAllOrderByTime(any(String.class),any(String.class))).thenReturn(this.test_orderstat_list);
        lenient().when(this.orderService.getOrderByOrderId(any(Integer.class))).thenReturn(this.test_order);
        lenient().when(this.orderService.getOrder()).thenReturn(this.test_order_list);
        lenient().when(this.orderService.getAllOrder()).thenReturn(this.test_order_list);
    }

    @Test
    void getOrderByTime() {
        assertEquals(this.orderController.getOrderByTime("",""),this.test_orderstat_list);
    }

    @Test
    void getAllOrderByTime() {
        assertEquals(this.orderController.getAllOrderByTime("",""),this.test_orderstat_list);
    }

    @Test
    void getOrderByOrderId() {
        assertEquals(this.orderController.getOrderByOrderId(1),this.test_order);
    }

    @Test
    void getOrder() {
        String jsonOutput= JSON.toJSONString(this.test_order_list, SerializerFeature.DisableCircularReferenceDetect);
        assertEquals(this.orderController.getOrder(),jsonOutput);
    }

    @Test
    void getAllOrder() {
        String jsonOutput= JSON.toJSONString(this.test_order_list, SerializerFeature.DisableCircularReferenceDetect);
        assertEquals(this.orderController.getAllOrder(),jsonOutput);
    }
}