package com.rr.microservice.order.service;

import com.rr.microservice.order.client.InventoryClient;
import com.rr.microservice.order.dto.OrderRequest;
import com.rr.microservice.order.model.Order;
import com.rr.microservice.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
