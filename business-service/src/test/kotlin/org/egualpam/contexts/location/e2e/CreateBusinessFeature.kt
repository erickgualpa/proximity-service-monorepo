package org.egualpam.contexts.location.e2e

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test

class CreateBusinessFeature : AbstractIntegrationTest() {
  @Test
  fun `create business`() {
    webTestClient.post()
        .uri("/v1/businesses")
        .exchange()
        .expectStatus()
        .isCreated
  }
}
