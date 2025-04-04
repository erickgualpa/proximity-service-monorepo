package org.egualpam.contexts.location.e2e

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class RetrieveBusinessFeature : AbstractIntegrationTest() {

  @Test
  fun `retrieve business`() {
    val businessId = randomUUID().toString()
    val address = "123 Market Street"
    val city = "San Francisco"
    val state = "CA"
    val country = "USA"
    val latitude = 37.7749
    val longitude = -122.4194

    createTestBusiness.run(
        id = businessId,
        address = address,
        city = city,
        state = state,
        country = country,
        latitude = latitude,
        longitude = longitude,
    )

    val expected =
        """
        {
          "id": "$businessId",
          "address": "$address",
          "city": "$city",
          "state": "$state",
          "country": "$country",
          "latitude": "$latitude",
          "longitude": "$longitude"
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
