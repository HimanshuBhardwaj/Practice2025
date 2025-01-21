package org.phonepe.assignment.service.order;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
@AllArgsConstructor
public class OrderExecuter implements Runnable {
    private final OrdersDataBaseI ordersDB;

    @SneakyThrows
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            processOrders();
        }
    }

    private void processOrders() {
        synchronized (ordersDB) {
            for (StockSymbol stockSymbol : ordersDB.getAllStockSymbol()) {
                List<Order> sellOrders = ordersDB.getAcceptedSellOrders(stockSymbol);
                for (Order sellOrder : sellOrders) {
                    matchOrders(stockSymbol, sellOrder);
                }
            }
        }
    }

    private void matchOrders(StockSymbol stockSymbol, Order sellOrder) {
        List<Order> buyOrders = ordersDB.getAcceptedBuyOrders(stockSymbol);
        for (Order buyOrder : buyOrders) {
            if (canMatchOrders(sellOrder, buyOrder)) {
                executeTrade(sellOrder, buyOrder);
                break;
            }
        }
    }

    private boolean canMatchOrders(Order sellOrder, Order buyOrder) {
        return sellOrder.getPrice() <= buyOrder.getPrice() &&
                sellOrder.getQuantity() == buyOrder.getQuantity();
    }


    private void executeTrade(Order sellOrder, Order buyOrder) {
        ordersDB.createOrderTrade(UUID.randomUUID(),
                buyOrder.getUserId(),
                sellOrder.getUserId(),
                buyOrder.getStockSymbol(),
                buyOrder.getQuantity(),
                buyOrder.getPrice(),
                System.currentTimeMillis());
        ordersDB.executeOrders(buyOrder);
        ordersDB.executeOrders(sellOrder);
    }

    /*@SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);

        while (!Thread.currentThread().isInterrupted()) {
            synchronized (ordersDB) {
                //TODO: Remove it
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
    }*/
}
