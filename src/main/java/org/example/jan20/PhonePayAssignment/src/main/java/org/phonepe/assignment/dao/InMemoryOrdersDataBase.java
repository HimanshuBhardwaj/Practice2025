package org.phonepe.assignment.dao;

import lombok.ToString;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.OrderStatus;
import org.phonepe.assignment.model.OrderType;
import org.phonepe.assignment.model.StockSymbol;

import java.util.*;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
@ToString
public class InMemoryOrdersDataBase implements OrdersDataBaseI {
    private final TreeMap<UUID, Order> allOrders;
    private final TreeMap<StockSymbol,TreeSet<Order>> unExecutedOrders;

    public InMemoryOrdersDataBase() {
        this.unExecutedOrders = new TreeMap<>();
        this.allOrders = new TreeMap<>();
    }

    public void addOrder(Order order) {
        addStockSymbol(order.getStockSymbol());
        unExecutedOrders.get(order.getStockSymbol()).add(order);
        allOrders.put(order.getOrderId(),order);
    }

    public Order getOrder(UUID orderID) {
        return allOrders.get(orderID);
    }

    public List<StockSymbol> getAllStockSymbol() {
        return new ArrayList<>(unExecutedOrders.keySet());
    }

    public List<Order> getSellOrders(StockSymbol stockSymbol) {
        return getOrderByType(stockSymbol,OrderType.SELL);
    }

    public List<Order> getBuyOrders(StockSymbol stockSymbol) {
        return getOrderByType(stockSymbol,OrderType.BUY);
    }

    public void executeOrders(Order order) {
        order.setOrderStatus(OrderStatus.COMPLETED);
        unExecutedOrders.get(order.getStockSymbol()).remove(order);
    }

    private List<Order> getOrderByType(StockSymbol stockSymbol, OrderType orderType) {
        ArrayList<Order> sellOrders = new ArrayList<>();



        for (Order order: unExecutedOrders.get(stockSymbol)) {
            if (order.getOrderType()==orderType) {
                sellOrders.add(order);
            }
        }

        sellOrders.sort(getOrderSortByTimeComparator());

        return sellOrders;
    }

    private  Comparator<Order> getOrderSortByTimeComparator() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Long.compare(o1.getOrderAcceptedTimeStamp(), o2.getOrderAcceptedTimeStamp());
            }
        };
    }

    public void printDB() {
        System.out.print("All Orders: ");
        System.out.print(allOrders.size()+"\t");
        System.out.print("unExecutedOrders: ");

        int c=0;

        for (Map.Entry<StockSymbol, TreeSet<Order>> entry: unExecutedOrders.entrySet()) {
            for (Order o: entry.getValue()) {
                c++;
            }
        }

        System.out.println(c);
    }

    private void addStockSymbol(StockSymbol stockSymbol) {
        if (!unExecutedOrders.containsKey(stockSymbol)) {
            unExecutedOrders.put(stockSymbol,new TreeSet<>());
        }
    }
}
