package org.phonepe.assignment.service.order;

import org.phonepe.assignment.model.Order;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public interface OrderOperationsServiceI {
    void placeOrder(Order orders);
    Order orderStatus(UUID orderID );
    void modifyOrder(Order newOrder);
    void cancelOrder(UUID orderID);
}
