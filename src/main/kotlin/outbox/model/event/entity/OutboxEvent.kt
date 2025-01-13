package outbox.model.event.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

@Entity
@Table(name = "outbox_event")
@EntityListeners(AuditingEntityListener::class)
class OutboxEvent(
    @Column(name = "topic", nullable = false)
    val topic: String,

    @Column(name = "payload", nullable = false)
    val payload: String?,
) {
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: UUID? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    var createdAt: Instant? = null
        protected set

    @Column(name = "published_at")
    var publishedAt: Instant? = null
        protected set

    fun markPublished(timestamp: Instant) {
        publishedAt = timestamp
    }
}