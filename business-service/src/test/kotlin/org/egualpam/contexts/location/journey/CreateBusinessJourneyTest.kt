package org.egualpam.contexts.location.journey

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class CreateBusinessJourneyTest : AbstractIntegrationTest() {
  @Test
  fun `create business and verify it can be retrieved`() {
    val businessId = randomUUID().toString()

    // --- STEP 1: Create a business ---
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

    // --- STEP 2: Verify the business can be retrieved ---
    val response = """
      {
        "id": "$businessId",
        "address": "123 Main St",
        "city": "New York",
        "state": "NY",
        "country": "USA",
        "latitude": "40.7128",
        "longitude": "-74.006"
      }
    """.trimIndent()

    webTestClient.get()
        .uri("/v1/businesses/{business-id}", businessId)
        .exchange()
        .expectStatus()
        .isOk
        .expectHeader().contentType("application/json")
        .expectBody().json(response)
  }
}
