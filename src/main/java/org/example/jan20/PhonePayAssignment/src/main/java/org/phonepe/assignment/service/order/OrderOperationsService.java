package org.phonepe.assignment.service.order;

import org.phonepe.assignment.dao.OrdersDataBaseI;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.OrderStatus;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderOperationsService implements OrderOperationsServiceI {

    OrdersDataBaseI ordersDataBase;

    public OrderOperationsService(OrdersDataBaseI ordersDataBase) {
         this.ordersDataBase = ordersDataBase;
    }

    @Override
    public boolean placeOrder(Order order) {
        boolean orderPlacementResult=false;

        synchronized (ordersDataBase) {
            order.setOrderStatus(OrderStatus.ACCEPTED);
            ordersDataBase.addOrder(order);
            orderPlacementResult = true;
        }

        return orderPlacementResult;
    }

    @Override
    public Order orderStatus(UUID orderID) {
        synchronized (ordersDataBase) {
            return ordersDataBase.getOrder(orderID);
        }
    }

    private Order getDummyOrder(UUID orderID) {
        Order order = new Order();
        order.setOrderId(orderID);
        return order;
    }
}
