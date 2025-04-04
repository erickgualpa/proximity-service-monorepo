package org.egualpam.contexts.location.e2e.helper

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusinessCommand
import org.springframework.transaction.support.TransactionTemplate

class CreateTestBusiness(
  private val transactionTemplate: TransactionTemplate,
  private val createBusiness: CreateBusiness
) {
  fun run(
    id: String,
    address: String,
    city: String,
    state: String,
    country: String,
    latitude: Double,
    longitude: Double
  ) {
    val command = CreateBusinessCommand(
        id = id,
        addressStreet = address,
        addressCity = city,
        addressState = state,
        addressCountry = country,
        locationLatitude = latitude,
        locationLongitude = longitude,
    )

    transactionTemplate.executeWithoutResult {
      createBusiness.execute(command)
    }
  }
}
