package org.egualpam.contexts.location.health

import org.egualpam.contexts.location.shared.AbstractIntegrationTest
import org.junit.jupiter.api.Test

class ApplicationHealthCheck : AbstractIntegrationTest() {

  @Test
  fun `application status should be UP`() {
    webTestClient.get()
        .uri("/actuator/health")
        .exchange()
        .expectStatus()
        .isOk
        .expectBody()
        .json(
            """
              {
                "status": "UP"
              }
            """,
        )
  }
}
