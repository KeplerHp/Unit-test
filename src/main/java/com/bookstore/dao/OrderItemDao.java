package com.bookstore.dao;

import com.bookstore.entity.OrderItem;
import java.util.List;

public interface OrderItemDao {
  List<OrderItem> getOrderItemById(Integer orderId);
}

