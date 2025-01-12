package outbox.model.gc

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import outbox.model.event.entity.OutboxEventRepo
import java.time.Instant

@Service
class GarbageCollector(
    private val props: GarbageCollectorProps,
    private val outboxEventRepo: OutboxEventRepo,
) {

    @Scheduled(cron = "\${outbox.gc.cron}")
    fun deleteOldEvents() {
        val expirationDate = Instant.now().minus(props.ttl)
        outboxEventRepo.deleteOldEvents(expirationDate)
    }
}