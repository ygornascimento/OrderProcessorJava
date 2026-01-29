package br.tec.itlabs.orders.worker.messaging;

import br.tec.itlabs.orders.worker.dto.OrderCreatedEvent;
import br.tec.itlabs.orders.worker.persistence.OrderEntity;
import br.tec.itlabs.orders.worker.persistence.OrderRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrdersCreatedListener {

    private final OrderRepository repository;

    public OrdersCreatedListener(OrderRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = "${app.kafka.topics.order-created}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(OrderCreatedEvent event, Acknowledgment ack) {
        try {
            OrderEntity entity = new OrderEntity(
                    event.getId(),
                    event.getCustomerName(),
                    event.getAmount(),
                    event.getOrderDate()
            );

            repository.save(entity);

            // só comita offset depois de persistir
            ack.acknowledge();
        } catch (DataIntegrityViolationException dup) {
            // provável duplicate key -> idempotência
            ack.acknowledge();
        }
    }
}
