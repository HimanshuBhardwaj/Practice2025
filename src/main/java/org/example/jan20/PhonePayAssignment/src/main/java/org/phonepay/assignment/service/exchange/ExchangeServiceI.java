package org.phonepay.assignment.service.exchange;

import org.phonepay.assignment.model.Order;

import java.util.UUID;

public interface ExchangeServiceI {
    boolean placeOrder(Order orders);
    Order orderStatus(UUID uuid, UUID orderID );
}
