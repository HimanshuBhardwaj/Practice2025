package org.phonepe.assignment.service.exchange;

import org.phonepe.assignment.exception.InvalidUser;
import org.phonepe.assignment.exception.OrderAccessDeniedException;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.service.order.OrderConsumerServiceI;
import org.phonepe.assignment.service.order.OrderOperationsServiceI;
import org.phonepe.assignment.service.user.UserServiceI;
import java.util.UUID;


/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class ExchangeController implements ExchangeControllerI {

    UserServiceI userService;
    OrderOperationsServiceI orderProducerService;
    OrderConsumerServiceI orderConsumerService;

    public ExchangeController(UserServiceI userServiceI, OrderOperationsServiceI orderServiceI, OrderConsumerServiceI orderConsumerService) {
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
