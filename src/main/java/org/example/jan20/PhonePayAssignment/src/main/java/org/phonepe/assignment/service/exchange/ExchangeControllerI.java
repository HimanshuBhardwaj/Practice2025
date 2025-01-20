package org.phonepe.assignment.service.exchange;

import org.phonepe.assignment.model.Order;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public interface ExchangeControllerI {
    void placeOrder(Order orders);
    Order orderStatus(UUID userID, UUID orderID );
    void modifyOrder(Order newOrder);
    void cancelOrder(UUID orderID,UUID userId);
}
