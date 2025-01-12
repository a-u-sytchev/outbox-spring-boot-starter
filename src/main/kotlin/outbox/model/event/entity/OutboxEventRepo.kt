package outbox.model.event.entity

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
interface OutboxEventRepo : JpaRepository<OutboxEvent, UUID> {

    @Query("select e from OutboxEvent e where e.publishedAt is null")
    fun findAllNotPublished(): List<OutboxEvent>

    @Transactional
    @Modifying
    @Query("delete from EventOutbox where (publishedAt is not null) and (createdAt < :date)")
    fun deleteOldEvents(@Param("date") date: Instant)
}