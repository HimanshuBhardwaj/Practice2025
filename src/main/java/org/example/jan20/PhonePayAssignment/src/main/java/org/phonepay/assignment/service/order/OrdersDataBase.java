package org.phonepay.assignment.service.order;

import org.phonepay.assignment.model.Order;
import org.phonepay.assignment.model.OrderStatus;
import org.phonepay.assignment.model.OrderType;
import org.phonepay.assignment.model.StockSymbol;

import java.util.*;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class OrdersDataBase {
    private TreeMap<UUID, Order> ordersTable;
    private TreeMap<StockSymbol,TreeSet<Order>> unexecutedOrders;

    public OrdersDataBase(Queue<Order> ordersQueued,TreeSet<Order> ordersExecuted, TreeSet<Order> ordersInQueue) {
        this.unexecutedOrders = new TreeMap<>();
        this.ordersTable = new TreeMap<>();
    }

    public void addOrder(Order order) {
        addStockSymbol(order.getStockSymbol());
        unexecutedOrders.get(order.getStockSymbol()).add(order);
        ordersTable.put(order.getOrderId(),order);
    }

    private void addStockSymbol(StockSymbol stockSymbol) {
        if (!unexecutedOrders.containsKey(stockSymbol)) {
            unexecutedOrders.put(stockSymbol,new TreeSet<>());
        }
    }

    public Order getOrder(UUID orderID) {
        return ordersTable.get(orderID);
    }

    public List<StockSymbol> getAllStockSymbol() {
        return new ArrayList<>(unexecutedOrders.keySet());
    }

    public ArrayList<Order> getSellOrders(StockSymbol stockSymbol) {
        ArrayList<Order> sellOrders = new ArrayList<>();

        for (Order order: unexecutedOrders.get(stockSymbol)) {
            if (order.getOrderType()== OrderType.SELL) {
                sellOrders.add(order);
            }
        }

        return sellOrders;
    }

    public ArrayList<Order> getBuyOrders(StockSymbol stockSymbol) {
        ArrayList<Order> sellOrders = new ArrayList<>();

        for (Order order: unexecutedOrders.get(stockSymbol)) {
            if (order.getOrderType()==OrderType.BUY) {
                sellOrders.add(order);
            }
        }

        return sellOrders;
    }

    public void executeOrders(Order order) {
        order.setOrderStatus(OrderStatus.COMPLETED);
        unexecutedOrders.get(order.getStockSymbol()).remove(order);
    }
}
