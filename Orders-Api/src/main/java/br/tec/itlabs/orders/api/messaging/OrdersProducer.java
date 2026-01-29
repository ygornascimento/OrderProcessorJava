package br.tec.itlabs.orders.api.messaging;

import br.tec.itlabs.orders.worker.dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrdersProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final String topic;

    public OrdersProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate,
                          @Value("${app.kafka.topics.order-created}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send(topic, event.getId(), event);
    }
}
