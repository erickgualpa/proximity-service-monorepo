package org.egualpam.contexts.location.e2e

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class RetrieveBusinessFeature : AbstractIntegrationTest() {
  @Test
  fun `retrieve business`() {
    val businessId = randomUUID().toString()
    webTestClient.get()
        .uri("/v1/businesses/{business-id}", businessId)
        .exchange()
        .expectStatus()
        .isOk
  }
}
