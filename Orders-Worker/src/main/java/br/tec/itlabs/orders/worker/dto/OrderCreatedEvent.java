package br.tec.itlabs.orders.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderCreatedEvent {
    private String id;
    private String customerName;
    private BigDecimal amount;
    private Instant orderDate;
}
