package outbox.model.gc

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties("outbox.gc")
data class GarbageCollectorProps(
    val ttl: Duration,
)
