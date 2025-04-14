package org.egualpam.contexts.location.shared

import org.egualpam.contexts.location.e2e.helper.ConsumeDomainEvents
import org.egualpam.contexts.location.e2e.helper.CreateTestBusiness
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.kafka.ConfluentKafkaContainer
import org.testcontainers.utility.DockerImageName.parse

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

    private const val KAFKA_NETWORK_ALIAS = "kafka"
    private const val KAFKA_CONNECT_NETWORK_ALIAS = "kafka-connect"

    private val defaultNetwork = Network.newNetwork()

    @ServiceConnection
    private val postgreSQLContainer = PostgreSQLContainer("postgres:latest")
        .withNetwork(defaultNetwork)

    @ServiceConnection
    private val kafkaContainer = ConfluentKafkaContainer("confluentinc/cp-kafka:7.8.0")
        .withNetwork(defaultNetwork)
        .withNetworkAliases(KAFKA_NETWORK_ALIAS)

    private val kafkaConnectContainer: GenericContainer<*> =
        GenericContainer(parse("confluentinc/cp-kafka-connect:7.8.0"))
            .withExposedPorts(8083)
            .withNetworkAliases(KAFKA_CONNECT_NETWORK_ALIAS)
            .withEnv(
                "CONNECT_BOOTSTRAP_SERVERS",
                // TODO: Check why this worked with port '9093', and how 'KAFKA_ADVERTISED_LISTENERS' should be properly set
                "$KAFKA_NETWORK_ALIAS:9093",
            )
            .withEnv("CONNECT_GROUP_ID", "quickstart")
            .withEnv("CONNECT_CONFIG_STORAGE_TOPIC", "quickstart-configs")
            .withEnv("CONNECT_OFFSET_STORAGE_TOPIC", "quickstart-offsets")
            .withEnv("CONNECT_STATUS_STORAGE_TOPIC", "quickstart-status")
            .withEnv("CONNECT_KEY_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
            .withEnv("CONNECT_VALUE_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
            .withEnv("CONNECT_REST_ADVERTISED_HOST_NAME", KAFKA_CONNECT_NETWORK_ALIAS)
            .dependsOn(postgreSQLContainer, kafkaContainer)
            .withNetwork(defaultNetwork)

    init {
      postgreSQLContainer.start()
      kafkaContainer.start()
      kafkaConnectContainer.start()
    }
  }
}
