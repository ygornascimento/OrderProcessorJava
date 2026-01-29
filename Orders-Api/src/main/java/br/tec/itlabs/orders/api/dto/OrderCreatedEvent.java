package br.tec.itlabs.orders.worker.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderCreatedEvent {
    private String id;
    private String customerName;
    private BigDecimal amount;
    private Instant orderDate;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String id, String customerName, BigDecimal amount, Instant orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
