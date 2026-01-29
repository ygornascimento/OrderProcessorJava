package br.tec.itlabs.orders.api.controller;

import br.tec.itlabs.orders.api.dto.CreateOrderRequest;
import br.tec.itlabs.orders.api.messaging.OrdersProducer;
import br.tec.itlabs.orders.worker.dto.OrderCreatedEvent;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersProducer producer;

    public OrdersController(OrdersProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@Valid @RequestBody CreateOrderRequest request) {
        String id = UUID.randomUUID().toString();
        Instant orderDate = (request.getOrderDate() != null) ? request.getOrderDate() : Instant.now();

        OrderCreatedEvent event = new OrderCreatedEvent(id, request.getCustomerName(), request.getAmount(), orderDate);
        producer.publishOrderCreated(event);

        return ResponseEntity.accepted().body(Map.of("id", id));

    }
}
