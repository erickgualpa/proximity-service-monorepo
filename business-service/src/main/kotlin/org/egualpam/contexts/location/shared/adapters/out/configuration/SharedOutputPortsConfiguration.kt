package org.egualpam.contexts.location.shared.adapters.out.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.egualpam.contexts.location.shared.adapters.out.eventbus.TransactionalKafkaEventBus
import org.egualpam.contexts.location.shared.application.ports.out.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class SharedOutputPortsConfiguration {

  @Bean
  fun eventBus(
    jdbcTemplate: NamedParameterJdbcTemplate,
    objectMapper: ObjectMapper
  ): EventBus = TransactionalKafkaEventBus(jdbcTemplate, objectMapper)
}
