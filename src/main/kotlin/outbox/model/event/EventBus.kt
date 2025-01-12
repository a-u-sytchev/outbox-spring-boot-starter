package outbox.model.event

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import outbox.model.event.entity.OutboxEvent
import org.springframework.kafka.core.KafkaTemplate

@Service
class EventBus(
    private val kafkaTemplate: KafkaTemplate<String, ByteArray>,
) {
    fun sendEvent(event: OutboxEvent) {
        kafkaTemplate.send(
            ProducerRecord(event.topic, event.payload)
        )
    }
}