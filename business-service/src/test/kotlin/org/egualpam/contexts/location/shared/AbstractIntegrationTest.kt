package org.egualpam.contexts.location.shared

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer

@ActiveProfiles("integration-test")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [BusinessServiceSpringBootApplication::class],
)
abstract class AbstractIntegrationTest {

  @Autowired
  protected lateinit var webTestClient: WebTestClient

  companion object {

    const val TEST_ID = "6c485d83-4071-40a3-af25-cdfe8c5b7f1f"

    @ServiceConnection
    private val postgreSQLContainer = PostgreSQLContainer("postgres:latest")

    init {
      postgreSQLContainer.start()
    }
  }
}
