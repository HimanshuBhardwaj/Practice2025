package org.phonepe.assignment.dao;

import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.StockSymbol;
import java.util.List;
import java.util.UUID;

public interface OrdersDataBaseI {
    void addOrder(Order order);
    void executeOrders(Order order);
    List<Order> getBuyOrders(StockSymbol stockSymbol);
    List<Order> getSellOrders(StockSymbol stockSymbol);
    List<StockSymbol> getAllStockSymbol();
    Order getOrder(UUID orderID);
    void printDB();
}
