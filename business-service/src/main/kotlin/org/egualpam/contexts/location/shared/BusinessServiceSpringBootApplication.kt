package org.egualpam.contexts.location.shared

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
      // Business
      "org.egualpam.contexts.location.business.adapters.in.configuration",
      "org.egualpam.contexts.location.business.adapters.in.controllers",
      "org.egualpam.contexts.location.business.adapters.out.configuration",
      // Shared
      "org.egualpam.contexts.location.shared.adapters.out.configuration",
    ],
)
class BusinessServiceSpringBootApplication

fun main(args: Array<String>) {
  runApplication<BusinessServiceSpringBootApplication>(*args)
}
