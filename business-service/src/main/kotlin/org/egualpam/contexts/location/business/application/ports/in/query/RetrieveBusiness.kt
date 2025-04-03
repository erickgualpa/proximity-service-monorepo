package org.egualpam.contexts.location.business.application.ports.`in`.query

class RetrieveBusiness {

  fun execute(query: RetrieveBusinessQuery): BusinessDto {
    return BusinessDto(
        query.id,
        address = "123 Market Street",
        city = "San Francisco",
        state = "CA",
        country = "USA",
        latitude = "37.7749",
        longitude = "-122.4194",
    )
  }
}

data class RetrieveBusinessQuery(val id: String)

data class BusinessDto(
  val id: String,
  val address: String,
  val city: String,
  val state: String,
  val country: String,
  val latitude: String,
  val longitude: String
)
