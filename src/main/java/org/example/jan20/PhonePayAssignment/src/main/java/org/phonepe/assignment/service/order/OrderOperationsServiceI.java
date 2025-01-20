package org.phonepe.assignment.service.order;

import org.phonepe.assignment.model.Order;

import java.util.UUID;

public interface OrderOperationsServiceI {
    boolean placeOrder(Order orders);
    Order orderStatus(UUID orderID );
}
