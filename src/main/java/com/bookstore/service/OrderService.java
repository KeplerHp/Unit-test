package com.bookstore.service;

import com.bookstore.entity.Order;
import com.bookstore.entity.OrderStat;
import java.util.List;

public interface OrderService {
  List<Order> getOrder();

  Order getOrderByOrderId(Integer orderId);

  List<Order> getAllOrder();

  List<OrderStat> getOrderByTime(String t1, String t2);

  List<OrderStat> getAllOrderByTime(String t1, String t2);


}
