package outbox.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import outbox.model.event.OutboxEventPublisher
import outbox.model.event.OutboxManager
import outbox.model.event.entity.OutboxEventRepo

@Configuration
@EnableScheduling
class OutboxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun outboxService(
        outboxEventRepository: OutboxEventRepo
    ): OutboxManager {
        return OutboxManager(outboxEventRepository)
    }

    @Bean
    @ConditionalOnMissingBean
    fun outboxEventPublisher(
        outboxEventRepository: OutboxEventRepo
    ): OutboxEventPublisher {
        return OutboxEventPublisher(outboxEventRepository)
    }
}