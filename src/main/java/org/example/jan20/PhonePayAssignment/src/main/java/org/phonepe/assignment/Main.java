package org.phonepe.assignment;


import org.phonepe.assignment.dao.InMemoryOrdersDataBase;
import org.phonepe.assignment.dao.OrdersDataBaseI;
import org.phonepe.assignment.model.Order;
import org.phonepe.assignment.model.OrderType;
import org.phonepe.assignment.model.StockSymbol;
import org.phonepe.assignment.service.exchange.ExchangeController;
import org.phonepe.assignment.service.exchange.ExchangeControllerI;
import org.phonepe.assignment.service.order.*;
import org.phonepe.assignment.service.user.UserService;
import org.phonepe.assignment.service.user.UserServiceI;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        stockBuyAndSell_Success();
    }

    static void stockBuyAndSell_Success() throws InterruptedException {
        UserServiceI userService = new UserService();
        OrdersDataBaseI ordersDataBase = new InMemoryOrdersDataBase();
        OrderOperationsServiceI orderProducerService = new OrderOperationsService(ordersDataBase);
        OrderConsumerServiceI orderConsumerService = new OrderConsumerService(ordersDataBase);
        ExchangeControllerI exchangeService = new ExchangeController(userService, orderProducerService, orderConsumerService);


        Order order1 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.BUY, StockSymbol.RELIANCE, 2, 10);
        Order order2 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.SELL, StockSymbol.WIPRO, 2, 10);
        Order order3 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c62"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.SELL, StockSymbol.WIPRO, 2, 10);
        Order order4 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c63"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.BUY, StockSymbol.WIPRO, 2, 10);
        Order order5 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c64"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.SELL, StockSymbol.WIPRO, 2, 10);
        Order order6 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.BUY, StockSymbol.WIPRO, 2, 10);


        exchangeService.placeOrder(order1);
        exchangeService.placeOrder(order2);
        exchangeService.placeOrder(order3);
        exchangeService.placeOrder(order4);
        exchangeService.placeOrder(order5);
        exchangeService.placeOrder(order6);

        Thread.sleep(5000);

        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c62")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c63")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c64")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65")));

        Thread.sleep(10000);

        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c62")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c63")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c64")));
        System.out.println(exchangeService.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65")));
        Thread.sleep(5000);
    }
}