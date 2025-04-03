package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.egualpam.contexts.location.business.application.ports.`in`.query.BusinessDto
import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusinessQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.internalServerError
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/businesses")
class GetBusinessController(
  private val retrieveBusiness: RetrieveBusiness
) {

  private val logger: Logger = getLogger(this::class.java)

  @GetMapping("/{id}")
  fun get(@PathVariable id: String): ResponseEntity<BusinessResponse> {
    val query = RetrieveBusinessQuery(id)
    return try {
      val response = retrieveBusiness.execute(query).let {
        BusinessResponse.from(it)
      }
      ok(response)
    } catch (e: Exception) {
      logger.error("Error retrieving business with id [$id]", e)
      internalServerError().build()
    }
  }
}

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
