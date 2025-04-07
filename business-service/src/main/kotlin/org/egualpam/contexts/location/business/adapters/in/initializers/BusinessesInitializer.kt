package org.egualpam.contexts.location.business.adapters.`in`.initializers

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusinessCommand
import org.springframework.beans.factory.InitializingBean
import org.springframework.transaction.support.TransactionTemplate
import java.util.UUID.randomUUID

class BusinessesInitializer(
  private val transactionTemplate: TransactionTemplate,
  private val createBusiness: CreateBusiness
) : InitializingBean {
  override fun afterPropertiesSet() {
    repeat(100_000) {
      val command = CreateBusinessCommand(
          id = randomUUID().toString(),
          addressStreet = "Address $it",
          addressState = "State $it",
          addressCity = "City $it",
          addressCountry = "Country $it",
          locationLatitude = 20.0,
          locationLongitude = 30.0,
      )
      transactionTemplate.executeWithoutResult {
        createBusiness.execute(command)
      }
    }
  }
}
