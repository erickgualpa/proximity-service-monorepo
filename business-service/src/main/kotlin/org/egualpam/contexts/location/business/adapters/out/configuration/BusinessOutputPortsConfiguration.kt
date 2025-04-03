package org.egualpam.contexts.location.business.adapters.out.configuration

import org.egualpam.contexts.location.business.adapters.out.searchrepository.PostgreSQLSearchRepository
import org.egualpam.contexts.location.business.application.domain.Business
import org.egualpam.contexts.location.business.application.ports.out.Repository
import org.egualpam.contexts.location.business.application.ports.out.SearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class BusinessOutputPortsConfiguration {

  @Bean
  fun searchRepository(
    jdbcTemplate: NamedParameterJdbcTemplate
  ): SearchRepository = PostgreSQLSearchRepository(jdbcTemplate)

  @Bean
  fun repository() = object : Repository {
    override fun find(id: String): Business? {
      return null
    }

    override fun save(business: Business) {
    }
  }
}
