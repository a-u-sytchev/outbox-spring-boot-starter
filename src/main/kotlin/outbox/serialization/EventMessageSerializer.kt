package outbox.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import outbox.model.event.EventMessage
import java.util.UUID

class EventMessageSerializer : KSerializer<EventMessage> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EventMessage") {
        element<String>("eventId")
        element<String?>("message", isOptional = true)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: EventMessage) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.eventId.toString())
            encodeNullableSerializableElement(descriptor, 1, String.serializer(), value.message)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): EventMessage {
        return decoder.decodeStructure(descriptor) {
            val eventId = decodeElementIndex(descriptor).takeIf { it == 0 }
                ?.let { UUID.fromString(decodeStringElement(descriptor, 0)) }
                ?: throw SerializationException("Missing eventId")

            val message = decodeElementIndex(descriptor).takeIf { it == 1 }
                ?.let { decodeNullableSerializableElement(descriptor, 1, String.serializer()) }

            EventMessage(eventId, message)
        }
    }
}