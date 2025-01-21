package org.phonepe.assignment;


import lombok.extern.slf4j.Slf4j;
import org.phonepe.assignment.dao.InMemoryOrdersDBRepo;
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

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.stockBuyAndSellModifiedAndCandCancelledOrder_Success();
        //main.stockBuyAndSell_Success();
        //main.stockCancelOrder_Success();
        //main.stockModifyOrder_Success();
    }

    void stockModifyOrder_Success() {
        ExchangeControllerI exchangeService = getExchangeController();
        placeBulkOrder(exchangeService);
        Order order = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.BUY, StockSymbol.FLIPKART, 2, 10);
        Order order1 = new Order(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65"), UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), OrderType.BUY, StockSymbol.FLIPKART, 2, 100);

        exchangeService.modifyOrder(order);
        exchangeService.modifyOrder(order1);

        printOrderStatus(exchangeService);
    }

    void stockCancelOrder_Success() {
        ExchangeControllerI exchangeService = getExchangeController();
        placeBulkOrder(exchangeService);

        exchangeService.cancelOrder(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60"),UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"));
        exchangeService.cancelOrder(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61"),UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"));

        printOrderStatus(exchangeService);
    }

    void stockBuyAndSellModifiedAndCandCancelledOrder_Success() throws InterruptedException {
        ExchangeControllerI exchangeController = getExchangeController();
        placeBulkOrder(exchangeController);

        printOrderStatus(exchangeController);
        log.info("Modifying Order");
        Order order = exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65"));
        order.setStockSymbol(StockSymbol.RELIANCE);
        order.setPrice(8);
        order.setOrderType(OrderType.SELL);

        exchangeController.modifyOrder(order);

        order = exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61"));
        order.setPrice(12);
        order.setOrderType(OrderType.BUY);

        exchangeController.modifyOrder(order);

        exchangeController.cancelOrder(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c62"),UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"));
        exchangeController.cancelOrder(UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c63"),UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"));

        printOrderStatus(exchangeController);
        Thread.sleep(5000);
        //exchangeController.logDBState();
        log.info("Printing order status after all operations");
        printOrderStatus(exchangeController);
        exchangeController.logDBState();
    }

    void stockBuyAndSell_Success() throws InterruptedException {
        ExchangeControllerI exchangeController = getExchangeController();
        exchangeController.logDBState();
        placeBulkOrder(exchangeController);
        Thread.sleep(5000);
        exchangeController.logDBState();
    }

    private ExchangeControllerI getExchangeController() {
        UserServiceI userService = new UserService();
        OrdersDataBaseI ordersDataBase = new InMemoryOrdersDBRepo();
        OrderOperationsServiceI orderOperationsService = new OrderOperationsService(ordersDataBase);
        OrderExecutionServiceI orderExecutionService = new OrderExecutionService(ordersDataBase);
         return new ExchangeController(userService, orderOperationsService, orderExecutionService);
    }

    private void printOrderStatus(ExchangeControllerI exchangeController) {
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c60")).toString());
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c61")).toString());
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c62")).toString());
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c63")).toString());
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c64")).toString());
        log.info(exchangeController.orderStatus(UUID.fromString("c9d30604-9400-4b2b-a4cd-1b4fd8436c60"), UUID.fromString("d9d30604-9400-4b2b-a4cd-1b4fd8436c65")).toString());
    }

    private void placeBulkOrder(ExchangeControllerI exchangeService) {
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
    }
}