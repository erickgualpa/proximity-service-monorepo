package org.egualpam.contexts.location.business.adapters.`in`.configuration

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusiness
import org.egualpam.contexts.location.business.application.ports.out.BusinessRepository
import org.egualpam.contexts.location.business.application.ports.out.BusinessSearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessInputPortsConfiguration {

  @Bean
  fun retrieveBusiness(businessSearchRepository: BusinessSearchRepository) = RetrieveBusiness(businessSearchRepository)

  @Bean
  fun createBusiness(businessRepository: BusinessRepository) = CreateBusiness(businessRepository)
}
