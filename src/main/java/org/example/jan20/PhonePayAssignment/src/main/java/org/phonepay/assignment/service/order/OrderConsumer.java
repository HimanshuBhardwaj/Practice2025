package org.phonepay.assignment.service.order;

import org.phonepay.assignment.model.Order;
import org.phonepay.assignment.model.OrderStatus;
import org.phonepay.assignment.model.StockSymbol;

import java.util.ArrayList;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrderConsumer implements Runnable{
    private final OrdersDataBase ordersDB;

    public OrderConsumer(OrdersDataBase ordersDataBase) {
        this.ordersDB = ordersDataBase;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (ordersDB) {
                for (StockSymbol stockSymbol: ordersDB.getAllStockSymbol()) {
                    ArrayList<Order> sellOrders = ordersDB.getSellOrders(stockSymbol);
                    ArrayList<Order> buyOrders = ordersDB.getBuyOrders(stockSymbol);

                    // Assumption: Only Supporting exact quantity to match. But in future this could be extended to non-matching quantity also.
                    for (Order sellOrder: sellOrders) {
                        for (Order buyOrder: buyOrders) {
                            if (buyOrder.getOrderStatus() != OrderStatus.COMPLETED
                                    && sellOrder.getPrice()<=buyOrder.getPrice()
                                    && sellOrder.getQuantity()==buyOrder.getQuantity()) {
                                ordersDB.executeOrders(buyOrder);
                                ordersDB.executeOrders(sellOrder);
                            }
                        }
                    }
                }
            }
        }
    }
}
