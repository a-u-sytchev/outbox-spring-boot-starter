package outbox.model.event

import kotlinx.serialization.Serializable
import outbox.serialization.EventMessageSerializer
import java.util.UUID

@Serializable(with = EventMessageSerializer::class)
data class EventMessage(
    val eventId: UUID,
    val message: String?,
)