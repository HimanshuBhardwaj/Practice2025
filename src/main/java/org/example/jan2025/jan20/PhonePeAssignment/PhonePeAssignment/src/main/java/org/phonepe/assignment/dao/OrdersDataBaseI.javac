package org.phonepe.assignment.dao;

import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.StockSymbol;
import java.util.List;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public interface OrdersDataBaseI {
    void addOrder(Order order);
    void executeOrders(Order order);
    List<Order> getAcceptedBuyOrders(StockSymbol stockSymbol);
    List<Order> getAcceptedSellOrders(StockSymbol stockSymbol);
    List<StockSymbol> getAllStockSymbol();
    Order getOrder(UUID orderID);
    void logDBSummary();
    void deleteOrder(Order oldOrder);
    void cancelOrderExecution(Order order);
    void createOrderTrade(UUID uuid, UUID buyerId, UUID sellerId, StockSymbol stockSymbol, double quantity, double price, long tradeTimeStamp);
}
