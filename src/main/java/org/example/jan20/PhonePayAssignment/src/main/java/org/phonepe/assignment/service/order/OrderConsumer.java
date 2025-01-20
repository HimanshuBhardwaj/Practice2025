package org.phonepe.assignment.service.order;

import org.phonepe.assignment.dao.OrdersDataBaseI;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.OrderStatus;
import org.phonepe.assignment.model.StockSymbol;
import java.util.List;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderConsumer implements Runnable{
    private final OrdersDataBaseI ordersDB;

    public OrderConsumer(OrdersDataBaseI ordersDataBase) {
        this.ordersDB = ordersDataBase;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ordersDB.printDBSummary();

            synchronized (ordersDB) {
                for (StockSymbol stockSymbol: ordersDB.getAllStockSymbol()) {
                    List<Order> sellOrders = ordersDB.getSellOrders(stockSymbol);
                    // Assumption: Only Supporting exact quantity to match. But in future this could be extended to non-matching quantity also.
                    for (Order sellOrder: sellOrders) {
                        List<Order> buyOrders = ordersDB.getBuyOrders(stockSymbol);
                        for (Order buyOrder: buyOrders) {
                            if (buyOrder.getOrderStatus() != OrderStatus.COMPLETED
                                    && sellOrder.getPrice()<=buyOrder.getPrice()
                                    && sellOrder.getQuantity()==buyOrder.getQuantity()) {
                                ordersDB.createOrderTrade(UUID.randomUUID(),buyOrder.getUserId(),sellOrder.getUserId(),buyOrder.getStockSymbol(),buyOrder.getQuantity(),buyOrder.getPrice(), System.currentTimeMillis());
                                ordersDB.executeOrders(buyOrder);
                                ordersDB.executeOrders(sellOrder);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
