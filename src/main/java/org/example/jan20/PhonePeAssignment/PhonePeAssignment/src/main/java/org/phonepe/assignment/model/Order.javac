package org.phonepe.assignment.model;

import lombok.*;
import org.phonepe.assignment.exception.InternalServerError;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Comparable<Order>, Cloneable{
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

    public Order(@NonNull UUID orderId, @NonNull UUID userId, OrderType orderType, StockSymbol stockSymbol, double quantity, double price) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderType = orderType;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.orderAcceptedTimeStamp = System.currentTimeMillis();
        this.orderStatus=OrderStatus.CREATED;
    }

    @Override
    public int compareTo(Order o) {
        return this.orderId.compareTo(o.orderId);
    }

    @Override
    public Order clone() {
        try {
            return (Order) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
