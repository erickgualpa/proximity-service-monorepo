package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.command.CreateBusinessCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.internalServerError
import org.springframework.http.ResponseEntity.status
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/businesses")
class PostBusinessController(
  private val transactionTemplate: TransactionTemplate,
  private val createBusiness: CreateBusiness
) {

  private val logger: Logger = getLogger(this::class.java)

  @PostMapping
  fun post(@RequestBody request: PostBusinessRequest): ResponseEntity<Void> {
    return try {
      transactionTemplate.executeWithoutResult {
        request.toCommand().let { createBusiness.execute(it) }
      }
      status(CREATED).build()
    } catch (e: Exception) {
      logger.error("Error creating business with request [$request]", e)
      internalServerError().build()
    }
  }
}

data class PostBusinessRequest(
  val id: String,
  val address: Address,
  val location: Location
) {
  data class Address(
    val street: String,
    val city: String,
    val state: String,
    val country: String
  )

  data class Location(
    val latitude: Double,
    val longitude: Double
  )

  fun toCommand() = CreateBusinessCommand(
      id = this.id,
      addressStreet = this.address.street,
      addressCity = this.address.city,
      addressState = this.address.state,
      addressCountry = this.address.country,
      locationLatitude = this.location.latitude,
      locationLongitude = this.location.longitude,
  )
}
