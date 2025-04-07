package org.egualpam.contexts.location.business.adapters.`in`.initializers

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusinessCommand
import org.springframework.beans.factory.InitializingBean
import org.springframework.transaction.support.TransactionTemplate
import java.util.UUID.randomUUID
import java.util.concurrent.Executors
import kotlin.random.Random

class BusinessesInitializer(
  private val transactionTemplate: TransactionTemplate,
  private val createBusiness: CreateBusiness
) : InitializingBean {
  override fun afterPropertiesSet() {
    Executors.newFixedThreadPool(10)
        .use { executorService ->
          repeat(100_000) {
            val latitude = Random.nextDouble(-90.0, 90.0)
            val longitude = Random.nextDouble(-180.0, 180.0)

            val command = CreateBusinessCommand(
                id = randomUUID().toString(),
                addressStreet = "Address $it",
                addressState = "State $it",
                addressCity = "City $it",
                addressCountry = "Country $it",
                locationLatitude = latitude,
                locationLongitude = longitude,
            )

            executorService.submit {
              transactionTemplate.execute {
                createBusiness.execute(command)
              }
            }
          }
        }
  }
}
