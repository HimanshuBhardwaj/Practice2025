package org.phonepay.assignment.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
@Setter
@Getter
public class Order implements Comparable<Order>{
    @NonNull
    UUID orderId;
    @NonNull
    UUID userId;
    OrderType orderType;
    StockSymbol stockSymbol;
    double quantity;
    double price;
    long orderAcceptedTimeStamp;
    OrderStatus orderStatus;

    @Override
    public int compareTo(Order o) {
        return this.orderId.compareTo(o.orderId);
    }
}
