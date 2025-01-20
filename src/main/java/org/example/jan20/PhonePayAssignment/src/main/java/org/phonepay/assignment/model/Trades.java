package org.phonepay.assignment.model;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class Trades {
    UUID traderID;
    TradeType tradeType;
    UUID buyerOrderID;
    UUID selledOrderID;
    StockSymbol stockSymbol;
    double quantity;
    double price;
    long tradeTimeStamp;
}
