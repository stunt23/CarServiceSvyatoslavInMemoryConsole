package org.example.StorageInterf;

import org.example.Entity.Order;
import org.example.Entity.Repairer;

import java.util.List;
import java.util.Map;

public interface OrderStorageInterf {
    long save(Order order);
    List<Order> findAll();

    Map<Long, Order> getOrders();

}
