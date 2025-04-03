package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusiness
import org.egualpam.contexts.location.business.application.ports.`in`.query.RetrieveBusinessQuery
import org.springframework.http.ResponseEntity
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

  @GetMapping("/{id}")
  fun get(@PathVariable id: String): ResponseEntity<BusinessResponse> {
    val query = RetrieveBusinessQuery(id)
    val response = retrieveBusiness.execute(query).let {
      BusinessResponse.from(it)
    }
    return ok(response)
  }
}
