package org.egualpam.contexts.location.e2e

import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID
import java.util.concurrent.TimeUnit.SECONDS

class CreateBusinessFeature : AbstractIntegrationTest() {
  @Test
  fun `create business`() {
    val businessId = randomUUID().toString()

    val addressCity = "New York"
    val addressStreet = "123 Main St"
    val addressState = "NY"
    val addressCountry = "USA"

    val locationLatitude = 40.7128
    val locationLongitude = -74.0060

    val request = """
      {
        "id": "$businessId",
        "address": {
          "street": "$addressStreet",
          "city": "$addressCity",
          "state": "$addressState",
          "country": "$addressCountry"
        },
        "location": {
          "latitude": $locationLatitude,
          "longitude": $locationLongitude
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

    await().atMost(10, SECONDS)
        .untilAsserted {
          val events = consumeDomainEvents.all()
          assertThat(events).anySatisfy {
            assertThat(it.type).isEqualTo("BusinessCreated")
            assertThat(it.version).isEqualTo("1.0")
            assertThat(it.entityId).isEqualTo(businessId)

            assertThat(it.data).containsExactlyInAnyOrderEntriesOf(
                mapOf(
                    "addressCity" to addressCity,
                    "addressCountry" to addressCountry,
                    "addressState" to addressState,
                    "addressStreet" to addressStreet,
                    "locationLatitude" to locationLatitude,
                    "locationLongitude" to locationLongitude,
                ),
            )
          }
        }
  }
}
