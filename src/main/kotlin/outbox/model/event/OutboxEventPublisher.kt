package outbox.model.event

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import outbox.model.event.entity.OutboxEventRepo
import java.time.Instant

@Component
class OutboxEventPublisher(
    private val eventBus: EventBus,
    private val outboxEventRepo: OutboxEventRepo,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "\${outbox.schedule.cron}")
    @Transactional
    fun publishEvents() {
        outboxEventRepo.findAllNotPublished().forEach { event ->
            try {
                eventBus.sendEvent(event)

                event.apply {
                    markPublished(Instant.now())
                    outboxEventRepo.save(this)
                }
            } catch (e: Exception) {
                log.error("Error sending event with id=${event.id}", e)
            }
        }
    }
}