package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.egualpam.contexts.location.business.application.ports.`in`.query.BusinessDto

data class BusinessResponse(
  val id: String,
  val address: String,
  val city: String,
  val state: String,
  val country: String,
  val latitude: String,
  val longitude: String
) {
  companion object {
    fun from(dto: BusinessDto) = BusinessResponse(
        id = dto.id,
        address = dto.address,
        city = dto.city,
        state = dto.state,
        country = dto.country,
        latitude = dto.latitude,
        longitude = dto.longitude,
    )
  }
}
