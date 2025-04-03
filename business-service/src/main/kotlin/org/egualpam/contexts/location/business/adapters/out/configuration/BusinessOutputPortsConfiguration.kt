package org.egualpam.contexts.location.business.adapters.out.configuration

import org.egualpam.contexts.location.business.adapters.out.repository.PostgreSQLBusinessRepository
import org.egualpam.contexts.location.business.adapters.out.searchrepository.PostgreSQLSearchRepository
import org.egualpam.contexts.location.business.application.ports.out.BusinessRepository
import org.egualpam.contexts.location.business.application.ports.out.BusinessSearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class BusinessOutputPortsConfiguration {

  @Bean
  fun businessSearchRepository(
    jdbcTemplate: NamedParameterJdbcTemplate
  ): BusinessSearchRepository = PostgreSQLSearchRepository(jdbcTemplate)

  @Bean
  fun businessRepository(
    jdbcTemplate: NamedParameterJdbcTemplate
  ): BusinessRepository = PostgreSQLBusinessRepository(jdbcTemplate)
}
