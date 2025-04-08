package org.egualpam.contexts.location.shared

import org.egualpam.contexts.location.e2e.helper.ConsumeDomainEvents
import org.egualpam.contexts.location.e2e.helper.CreateTestBusiness
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

  @Autowired
  protected lateinit var createTestBusiness: CreateTestBusiness

  @Autowired
  protected lateinit var consumeDomainEvents: ConsumeDomainEvents

  companion object {

    @ServiceConnection
    private val postgreSQLContainer = PostgreSQLContainer("postgres:latest")

    init {
      postgreSQLContainer.start()
    }
  }
}
