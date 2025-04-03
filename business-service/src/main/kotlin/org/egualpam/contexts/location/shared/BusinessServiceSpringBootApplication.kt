package org.egualpam.contexts.location.shared

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
      "org.egualpam.contexts.location.business.adapters.in.configuration",
      "org.egualpam.contexts.location.business.adapters.in.controllers",
    ],
)
class BusinessServiceSpringBootApplication

fun main(args: Array<String>) {
  runApplication<BusinessServiceSpringBootApplication>(*args)
}
