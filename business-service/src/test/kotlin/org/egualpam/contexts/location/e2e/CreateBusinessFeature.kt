package org.egualpam.contexts.location.e2e

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class CreateBusinessFeature : AbstractIntegrationTest() {
  @Test
  fun `create business`() {
    val businessId = randomUUID().toString()

    val request = """
      {
        "id": "$businessId",
        "address": {
          "street": "123 Main St",
          "city": "New York",
          "state": "NY",
          "country": "USA"
        },
        "location": {
          "latitude": 40.7128,
          "longitude": -74.0060
        }
      }
    """.trimIndent()

    webTestClient.post()
        .uri("/v1/businesses")
        .header("Content-Type", "application/json")
        .bodyValue(request)
        .exchange()
        .expectStatus()
        .isCreated
  }
}
