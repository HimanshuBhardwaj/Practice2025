package org.phonepe.assignment.service.order;

import org.phonepe.assignment.dao.OrdersDataBaseI;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderExecutionService implements OrderExecutionServiceI {
    private final OrdersDataBaseI ordersDataBase;

    public OrderExecutionService(OrdersDataBaseI ordersDataBase) {
        this.ordersDataBase = ordersDataBase;
    }

    public void executeOrders() {
        OrderExecuter orderExecuter = new OrderExecuter(ordersDataBase);
        Thread thread = new Thread(orderExecuter);
        thread.setDaemon(true);
        thread.start();
    }
}
