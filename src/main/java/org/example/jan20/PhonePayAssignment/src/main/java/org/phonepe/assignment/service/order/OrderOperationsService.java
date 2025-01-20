package org.phonepe.assignment.service.order;

import org.phonepe.assignment.dao.OrdersDataBaseI;
import org.phonepe.assignment.exception.InvalidOrderOperationException;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.OrderStatus;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderOperationsService implements OrderOperationsServiceI {

    private final OrdersDataBaseI ordersDB;

    public OrderOperationsService(OrdersDataBaseI ordersDataBase) {
         this.ordersDB = ordersDataBase;
    }

    @Override
    public void placeOrder(Order order) {

        synchronized (ordersDB) {
            ordersDB.addOrder(order);
        }
    }

    @Override
    public Order orderStatus(UUID orderID) {
        synchronized (ordersDB) {
            return ordersDB.getOrder(orderID);
        }
    }

    @Override
    public void modifyOrder(Order newOrder) {
        synchronized (ordersDB) {
            Order oldOrder = orderStatus(newOrder.getOrderId());

            if (oldOrder == null) {
                placeOrder(newOrder);
            } else {
                if (oldOrder.getOrderStatus() != OrderStatus.COMPLETED) {
                    ordersDB.deleteOrder(oldOrder);
                    ordersDB.addOrder(newOrder);
                } else {
                    throw new InvalidOrderOperationException("Order has already completed");
                }
            }
        }
    }

    @Override
    public void cancelOrder(UUID orderID) {
        synchronized (ordersDB) {
            Order order = orderStatus(orderID);

            if (order != null) {
                if (order.getOrderStatus() != OrderStatus.COMPLETED) {
                    ordersDB.cancelOrderExecution(order);
                } else {
                    throw new InvalidOrderOperationException("Order has already completed");
                }
            }
        }
    }
}
