package org.phonepe.assignment.service.exchange;

import org.phonepe.assignment.model.Order;

import java.util.UUID;

public interface ExchangeControllerI {
    boolean placeOrder(Order orders);
    Order orderStatus(UUID uuid, UUID orderID );
}
