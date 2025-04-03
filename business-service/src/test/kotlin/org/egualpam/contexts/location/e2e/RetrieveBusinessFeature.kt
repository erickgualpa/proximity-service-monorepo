package org.egualpam.contexts.location.e2e

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test

class RetrieveBusinessFeature : AbstractIntegrationTest() {
  @Test
  fun `retrieve business`() {
    // TODO: Replace by data inserted from the test
    val businessId = TEST_ID

    val expected =
        """
        {
          "id": "$businessId",
          "address": "123 Market Street",
          "city": "San Francisco",
          "state": "CA",
          "country": "USA",
          "latitude": "37.7749",
          "longitude": "-122.4194"
        }
        """

    webTestClient.get()
        .uri("/v1/businesses/{business-id}", businessId)
        .exchange()
        .expectStatus()
        .isOk
        .expectHeader().contentType("application/json")
        .expectBody().json(expected)
  }
}
