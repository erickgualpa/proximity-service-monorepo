package org.egualpam.contexts.location.business.adapters.`in`.configuration

import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusiness
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessInputPortsConfiguration {

  @Bean
  fun retrieveBusiness() = RetrieveBusiness()
}
