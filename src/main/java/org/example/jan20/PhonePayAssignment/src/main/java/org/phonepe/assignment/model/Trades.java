package org.phonepe.assignment.model;

import lombok.AllArgsConstructor;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/

@AllArgsConstructor
public class Trades {
    UUID traderID;
    TradeType tradeType;
    UUID buyerOrderID;
    UUID sellerOrderID;
    StockSymbol stockSymbol;
    double quantity;
    double price;
    long tradeTimeStamp;
}
