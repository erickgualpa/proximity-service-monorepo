package org.egualpam.contexts.location.business.adapters.`in`.controllers

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/businesses")
class PostBusinessController {

  @PostMapping
  fun post() = status(CREATED).build<Void>()
}
