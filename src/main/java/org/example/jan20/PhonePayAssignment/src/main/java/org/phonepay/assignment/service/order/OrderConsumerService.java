package org.phonepay.assignment.service.order;

import org.phonepay.assignment.model.Order;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderConsumerService implements OrderConsumerServiceI {
    private OrdersDataBase ordersDataBase;
    private ExecutorService executorService;

    public OrderConsumerService(OrdersDataBase ordersDataBase, int numThreads) {
        this.ordersDataBase = ordersDataBase;

        // Keeping it single threaded but scale it in the future.
        this.executorService= Executors.newSingleThreadExecutor();
    }

    public void executeOrders() {
        executorService.execute(new OrderConsumer(ordersDataBase));
    }
}
