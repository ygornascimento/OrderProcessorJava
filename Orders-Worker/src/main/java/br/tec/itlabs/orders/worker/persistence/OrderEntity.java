package br.tec.itlabs.orders.worker.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "id", length = 36, nullable = false, columnDefinition = "char(36)")
    private String id;

    @Column(name = "customer_name", nullable = false, length = 200)
    private String customerName;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, precision = 18, scale = 2)
    private Instant orderDate;

    protected OrderEntity() {
        //JPA aqui
    }

    public OrderEntity(String id, String customerName, BigDecimal amount, Instant orderDate) {
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
}
