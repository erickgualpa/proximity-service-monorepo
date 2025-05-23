package org.egualpam.contexts.location.business.application.ports.`in`.command

import org.egualpam.contexts.location.business.application.domain.Business
import org.egualpam.contexts.location.business.application.ports.out.BusinessRepository
import org.egualpam.contexts.location.shared.application.ports.out.EventBus

class CreateBusiness(
  private val repository: BusinessRepository,
  private val eventBus: EventBus
) {
  fun execute(command: CreateBusinessCommand) {
    command.let {
      val business = Business.create(
          id = it.id,
          addressStreet = it.addressStreet,
          addressCity = it.addressCity,
          addressState = it.addressState,
          addressCountry = it.addressCountry,
          locationLatitude = it.locationLatitude,
          locationLongitude = it.locationLongitude,
      )
      repository.save(business)
      eventBus.publish(business.domainEvents())
    }
  }
}

data class CreateBusinessCommand(
  val id: String,
  val addressStreet: String,
  val addressCity: String,
  val addressState: String,
  val addressCountry: String,
  val locationLatitude: Double,
  val locationLongitude: Double,
)
