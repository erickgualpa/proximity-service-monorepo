package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/businesses")
class GetBusinessController {

  @GetMapping("/{id}")
  fun get(@PathVariable id: String): ResponseEntity<BusinessResponse> {
    val response = BusinessResponse(
        id,
        address = "123 Market Street",
        city = "San Francisco",
        state = "CA",
        country = "USA",
        latitude = "37.7749",
        longitude = "-122.4194",
    )
    return ok(response)
  }
}
