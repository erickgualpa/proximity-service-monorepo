package org.egualpam.contexts.location.business.adapters.`in`.configuration

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.e2e.helper.CreateTestBusiness
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.support.TransactionTemplate

@Configuration
class E2EHelpersConfiguration {

  @Bean
  fun createTestBusiness(
    transactionTemplate: TransactionTemplate,
    createBusiness: CreateBusiness
  ) = CreateTestBusiness(transactionTemplate, createBusiness)
}
