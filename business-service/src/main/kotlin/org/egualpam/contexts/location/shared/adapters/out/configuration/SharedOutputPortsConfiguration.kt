package org.egualpam.contexts.location.shared.adapters.out.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect.CreateEventStoreConnector
import org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect.KafkaConnectDebeziumProperties
import org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect.KafkaConnectEventBus
import org.egualpam.contexts.location.shared.application.ports.out.EventBus
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
@EnableConfigurationProperties(KafkaConnectDebeziumProperties::class)
class SharedOutputPortsConfiguration {

  @Bean
  fun eventBus(
    jdbcTemplate: NamedParameterJdbcTemplate,
    objectMapper: ObjectMapper
  ): EventBus = KafkaConnectEventBus(jdbcTemplate, objectMapper)

  @Bean
  fun createEventStoreConnector(
    properties: KafkaConnectDebeziumProperties,
  ): InitializingBean = CreateEventStoreConnector(properties)
}
