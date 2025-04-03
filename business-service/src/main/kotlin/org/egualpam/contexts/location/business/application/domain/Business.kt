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
}
