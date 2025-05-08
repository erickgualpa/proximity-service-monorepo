package org.egualpam.contexts.location.business.application.domain

import org.egualpam.contexts.location.shared.application.domain.DomainEvent
import org.egualpam.contexts.location.shared.application.domain.DomainEventId
import org.egualpam.contexts.location.shared.application.domain.EntityId

class BusinessCreated(
  id: DomainEventId,
  businessId: EntityId,
  val addressStreet: String,
  val addressCity: String,
  val addressState: String,
  val addressCountry: String,
  val locationLatitude: Double,
  val locationLongitude: Double
) : DomainEvent(id, businessId)

class Business(
  private val id: EntityId,
  private val address: Address,
  private val location: Location,
) {

  private val domainEvents = mutableSetOf<DomainEvent>()

  companion object {
    fun create(
      id: String,
      addressStreet: String,
      addressCity: String,
      addressState: String,
      addressCountry: String,
      locationLatitude: Double,
      locationLongitude: Double,
    ): Business {
      val business = Business(
          id = EntityId(id),
          address = Address(addressStreet, addressCity, addressState, addressCountry),
          location = Location(locationLatitude, locationLongitude),
      )

      val event = BusinessCreated(
          id = DomainEventId.generate(),
          business.id,
          addressStreet = business.address.street,
          addressCity = business.address.city,
          addressState = business.address.state,
          addressCountry = business.address.country,
          locationLatitude = business.location.latitude,
          locationLongitude = business.location.longitude,
      )
      business.domainEvents.add(event)

      return business
    }
  }

  fun domainEvents() = domainEvents.toSet()

  fun id() = id.value
  fun addressStreet() = address.street
  fun addressCity() = address.city
  fun addressState() = address.state
  fun addressCountry() = address.country
  fun locationLatitude() = location.latitude
  fun locationLongitude() = location.longitude

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Business

    return id == other.id
  }

  override fun hashCode() = id.hashCode()
}
