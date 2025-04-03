package org.egualpam.contexts.location.business.adapters.out.configuration

import org.egualpam.contexts.location.business.application.ports.`in`.query.BusinessDto
import org.egualpam.contexts.location.business.application.ports.`in`.query.SearchResults
import org.egualpam.contexts.location.business.application.ports.out.SearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessOutputPortsConfiguration {

  @Bean
  fun searchRepository(): SearchRepository {
    return object : SearchRepository {
      override fun find(id: String): BusinessDto {
        return BusinessDto(
            id = id,
            address = "123 Market Street",
            city = "San Francisco",
            state = "CA",
            country = "USA",
            latitude = "37.7749",
            longitude = "-122.4194",
        )
      }

      override fun search(): SearchResults {
        TODO("Not yet implemented")
      }
    }
  }
}
