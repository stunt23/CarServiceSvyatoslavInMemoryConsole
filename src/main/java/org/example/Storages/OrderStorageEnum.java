package org.example.Storages;

import lombok.NonNull;
import lombok.ToString;
import org.example.Entity.Order;
import org.example.StorageInterf.OrderStorageInterf;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStorageEnum implements OrderStorageInterf {
    ORDER_STORAGE {
        private Map<Long, Order> orders = new HashMap<>();
        @ToString.Exclude
        private Long currentID = 1l;

        @Override
        public long save(@NonNull Order order) {
            orders.put(currentID, order);
            order.setId(currentID);
            currentID++;
            return currentID-1;
        }

        @Override
        public List<Order> findAll() {
            return orders.values().stream().sorted((Comparator.comparingLong(Order::getId)).
                    thenComparing(Comparator.comparing(Order::getPrice)).
                    thenComparing(Comparator.comparing(Order::getOpeningDate)).
                    thenComparing(Comparator.comparing(Order::getCompletionDate)).
                    thenComparing(Comparator.comparing(Order::getStatus))).toList();
        }

        public Map<Long, Order> getOrders() {
            return orders;
        }

        public void setOrders(Map<Long, Order> orders) {
            this.orders = orders;
        }

        public Long getCurrentID() {
            return currentID;
        }

        public void setCurrentID(Long currentID) {
            this.currentID = currentID;
        }
    }
}
