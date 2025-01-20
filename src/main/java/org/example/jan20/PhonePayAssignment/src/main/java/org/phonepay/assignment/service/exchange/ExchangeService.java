package org.phonepay.assignment.service.exchange;

import org.phonepay.assignment.exception.InvalidUser;
import org.phonepay.assignment.exception.OrderAccessDeniedException;
import org.phonepay.assignment.model.Order;
import org.phonepay.assignment.service.order.OrderConsumerServiceI;
import org.phonepay.assignment.service.order.OrderProducerServiceI;
import org.phonepay.assignment.service.user.UserServiceI;
import java.util.UUID;


/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class ExchangeService implements ExchangeServiceI{

    UserServiceI userService;
    OrderProducerServiceI orderProducerService;
    OrderConsumerServiceI orderConsumerService;

    public ExchangeService(UserServiceI userServiceI, OrderProducerServiceI orderServiceI, OrderConsumerServiceI orderConsumerService) {
        this.userService = userServiceI;
        this.orderProducerService = orderServiceI;
        this.orderConsumerService = orderConsumerService;
        orderConsumerService.executeOrders();
    }

    @Override
    public boolean placeOrder(Order orders) {
        validateUser(orders.getUserId());
        return orderProducerService.placeOrder(orders);
    }

    @Override
    public Order orderStatus(UUID uuid, UUID orderID) {
        validateUser(uuid);

        Order order = orderProducerService.orderStatus(orderID);

        if (order.getUserId().equals(uuid)) {
            return order;
        } else {
            throw new OrderAccessDeniedException("User is not allowed to access this order");
        }
    }

    private void validateUser(UUID userID) {
        if (!userService.isValidUser(userID)) {
            throw new InvalidUser("Invalid UserID: "+userID);
        }
    }
}
