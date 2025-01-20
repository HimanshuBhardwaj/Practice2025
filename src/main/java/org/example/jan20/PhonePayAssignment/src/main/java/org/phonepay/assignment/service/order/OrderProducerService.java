package org.phonepay.assignment.service.order;

import org.phonepay.assignment.model.Order;
import org.phonepay.assignment.model.OrderStatus;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderProducerService implements OrderProducerServiceI {

    OrdersDataBase ordersDataBase;

    public OrderProducerService() {
        LinkedList<Order> ordersQueued = new LinkedList<>();
        TreeSet<Order> ordersExecuted = new TreeSet<>();
        TreeSet<Order> ordersInQueue = new TreeSet<>();
         this.ordersDataBase = new OrdersDataBase(ordersQueued,ordersExecuted,ordersInQueue);
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
