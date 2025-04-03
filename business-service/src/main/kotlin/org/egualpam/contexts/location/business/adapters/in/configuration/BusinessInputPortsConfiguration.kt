package org.egualpam.contexts.location.business.adapters.`in`.configuration

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusiness
import org.egualpam.contexts.location.business.application.ports.out.Repository
import org.egualpam.contexts.location.business.application.ports.out.SearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessInputPortsConfiguration {

  @Bean
  fun retrieveBusiness(searchRepository: SearchRepository) = RetrieveBusiness(searchRepository)

  @Bean
  fun createBusiness(repository: Repository) = CreateBusiness(repository)
}
