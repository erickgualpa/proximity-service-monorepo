package org.egualpam.contexts.location.business.adapters.out.configuration

import org.egualpam.contexts.location.business.adapters.out.searchrepository.PostgreSQLSearchRepository
import org.egualpam.contexts.location.business.application.ports.out.SearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessOutputPortsConfiguration {

  @Bean
  fun searchRepository(): SearchRepository = PostgreSQLSearchRepository()
}
