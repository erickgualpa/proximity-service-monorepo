package org.egualpam.contexts.location.business.adapters.out.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.egualpam.contexts.location.e2e.helper.ConsumeDomainEvents
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessOutputPortsTestConfiguration {

  @Bean
  fun consumeDomainEvents(objectMapper: ObjectMapper) = ConsumeDomainEvents(objectMapper)
}
