package outbox.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import outbox.model.event.EventBus
import outbox.model.event.OutboxEventPublisher
import outbox.model.event.OutboxManager
import outbox.model.event.entity.OutboxEventRepo

@Configuration
@EnableScheduling
class OutboxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun outboxService(
        outboxEventRepo: OutboxEventRepo,
    ): OutboxManager {
        return OutboxManager(outboxEventRepo)
    }

    @Bean
    @ConditionalOnMissingBean
    fun outboxEventPublisher(
        eventBus: EventBus,
        outboxEventRepo: OutboxEventRepo,
    ): OutboxEventPublisher {
        return OutboxEventPublisher(eventBus, outboxEventRepo)
    }
}