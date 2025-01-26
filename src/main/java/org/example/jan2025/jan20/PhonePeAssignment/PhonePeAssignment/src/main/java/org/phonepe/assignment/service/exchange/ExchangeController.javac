package org.phonepe.assignment.service.exchange;

import org.phonepe.assignment.exception.InvalidUser;
import org.phonepe.assignment.exception.OrderAccessDeniedException;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.service.order.OrderExecutionServiceI;
import org.phonepe.assignment.service.order.OrderOperationsServiceI;
import org.phonepe.assignment.service.user.UserServiceI;
import java.util.UUID;


/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class ExchangeController implements ExchangeControllerI {

    private final UserServiceI userService;
    private final OrderOperationsServiceI orderOperationsService;

    public ExchangeController(UserServiceI userServiceI, OrderOperationsServiceI orderServiceI, OrderExecutionServiceI orderConsumerService) {
        this.userService = userServiceI;
        this.orderOperationsService = orderServiceI;
        orderConsumerService.executeOrders();
    }

    @Override
    public void placeOrder(Order orders) {
        validateUser(orders.getUserId());
        orderOperationsService.placeOrder(orders);
    }

    @Override
    public Order orderStatus(UUID userID, UUID orderID) {
        validateUser(userID);

        Order order = orderOperationsService.orderStatus(orderID);

        if (order!= null && order.getUserId().equals(userID)) {
            return order.clone();
        } else {
            throw new OrderAccessDeniedException("User is not allowed to access this order");
        }
    }

    @Override
    public void modifyOrder(Order newOrder) {
        validateUser(newOrder.getUserId());
        orderOperationsService.modifyOrder(newOrder);
    }

    @Override
    public void cancelOrder(UUID orderID, UUID userId) {
        validateUser(userId);
        orderOperationsService.cancelOrder(orderID);
    }

    @Override
    public void logDBState() {
        orderOperationsService.logDBState();
    }

    private void validateUser(UUID userID) {
        if (!userService.isValidUser(userID)) {
            throw new InvalidUser("Invalid UserID: "+userID);
        }
    }
}
