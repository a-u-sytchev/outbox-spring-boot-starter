package outbox.model.event

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import outbox.model.event.entity.OutboxEvent
import outbox.model.event.entity.OutboxEventRepo

@Service
class OutboxManager(
    private val outboxEventRepo: OutboxEventRepo,
) {
    @Transactional
    fun createEvent(topic: String, payload: ByteArray? = null) {
        outboxEventRepo.save(
            OutboxEvent(
                topic = topic,
                payload = payload,
            )
        )
    }
}