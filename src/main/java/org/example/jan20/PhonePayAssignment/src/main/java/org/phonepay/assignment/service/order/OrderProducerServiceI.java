package org.phonepay.assignment.service.order;

import org.phonepay.assignment.model.Order;

import java.util.UUID;

public interface OrderProducerServiceI {
    boolean placeOrder(Order orders);
    Order orderStatus(UUID orderID );
}
