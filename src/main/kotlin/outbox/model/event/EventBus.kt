package outbox.model.event

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import outbox.model.event.entity.OutboxEvent
import org.springframework.kafka.core.KafkaTemplate

@Service
class EventBus(
    private val json: Json,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    fun sendEvent(event: OutboxEvent) {
        kafkaTemplate.send(
            ProducerRecord(
                event.topic,
                json.encodeToString(
                    EventMessage(eventId = event.id!!, message = event.payload)
                ),
            )
        )
    }
}