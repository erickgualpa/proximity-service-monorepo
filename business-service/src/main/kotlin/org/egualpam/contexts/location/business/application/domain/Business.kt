package org.egualpam.contexts.location.business.application.domain

class Business(
  private val id: BusinessId,
  private val address: Address,
  private val location: Location,
) {

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
      return Business(
          id = BusinessId(id),
          address = Address(addressStreet, addressCity, addressState, addressCountry),
          location = Location(locationLatitude, locationLongitude),
      )
    }
  }

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
