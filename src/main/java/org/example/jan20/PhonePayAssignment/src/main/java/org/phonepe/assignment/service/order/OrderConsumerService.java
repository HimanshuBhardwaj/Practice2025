package org.phonepe.assignment.service.order;

import org.phonepe.assignment.dao.OrdersDataBaseI;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderConsumerService implements OrderConsumerServiceI {
    private OrdersDataBaseI ordersDataBase;

    public OrderConsumerService(OrdersDataBaseI ordersDataBase) {
        this.ordersDataBase = ordersDataBase;
    }

    public void executeOrders() {
        OrderConsumer orderConsumer = new OrderConsumer(ordersDataBase);
        Thread thread = new Thread(orderConsumer);
        thread.setDaemon(true);
        thread.start();
    }
}
