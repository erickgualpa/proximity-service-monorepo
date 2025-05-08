package org.egualpam.contexts.location.shared

import org.egualpam.contexts.location.e2e.helper.ConsumeDomainEvents
import org.egualpam.contexts.location.e2e.helper.CreateTestBusiness
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.kafka.ConfluentKafkaContainer
import org.testcontainers.utility.DockerImageName.parse
import org.testcontainers.utility.MountableFile

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

    private const val POSTGRES_NETWORK_ALIAS = "postgres"
    private const val POSTGRES_DEBEZIUM_USER = "debezium_user"
    private const val POSTGRES_DEBEZIUM_PASSWORD = "dbz"

    private const val KAFKA_NETWORK_ALIAS = "kafka"
    private const val KAFKA_TLS_PORT = 9093

    private const val DEBEZIUM_NETWORK_ALIAS = "debezium"
    private const val DEBEZIUM_PORT = 8083

    private val defaultNetwork = Network.newNetwork()

    @ServiceConnection
    private val postgreSQLContainer = PostgreSQLContainer("postgres:latest")
        .withNetwork(defaultNetwork)
        .withNetworkAliases(POSTGRES_NETWORK_ALIAS)
        .withCommand(
            "postgres",
            "-c", "wal_level=logical",
            "-c", "max_wal_senders=4",
            "-c", "max_replication_slots=4",
        )
        .withCopyFileToContainer(
            MountableFile.forClasspathResource("debezium/init-debezium-user.sql"),
            "/docker-entrypoint-initdb.d/init-debezium-user.sql",
        )

    @ServiceConnection
    private val kafkaContainer = ConfluentKafkaContainer("confluentinc/cp-kafka:7.8.0")
        .withNetwork(defaultNetwork)
        .withNetworkAliases(KAFKA_NETWORK_ALIAS)

    private val debeziumContainer = GenericContainer(parse("debezium/connect:2.5"))
        .withExposedPorts(DEBEZIUM_PORT)
        .withEnv("BOOTSTRAP_SERVERS", "$KAFKA_NETWORK_ALIAS:$KAFKA_TLS_PORT")
        .withEnv("GROUP_ID", "debezium")
        .withEnv("CONFIG_STORAGE_TOPIC", "debezium-connect-configs")
        .withEnv("OFFSET_STORAGE_TOPIC", "debezium-connect-offsets")
        .withEnv("STATUS_STORAGE_TOPIC", "debezium-connect-status")
        .withEnv("CONFIG_STORAGE_REPLICATION_FACTOR", "1")
        .withEnv("OFFSET_STORAGE_REPLICATION_FACTOR", "1")
        .withEnv("STATUS_STORAGE_REPLICATION_FACTOR", "1")
        .withEnv("KEY_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
        .withEnv("VALUE_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
        .withEnv("KEY_CONVERTER_SCHEMAS_ENABLE", "false")
        .withEnv("VALUE_CONVERTER_SCHEMAS_ENABLE", "false")
        .dependsOn(postgreSQLContainer, kafkaContainer)
        .withNetwork(defaultNetwork)
        .withNetworkAliases(DEBEZIUM_NETWORK_ALIAS)

    init {
      postgreSQLContainer.start()
      kafkaContainer.start()
      debeziumContainer.start()
    }

    @JvmStatic
    @DynamicPropertySource
    fun overrideDebeziumProperties(registry: DynamicPropertyRegistry) {
      registry.add("kafka-connect.debezium.host") { debeziumContainer.host }
      registry.add("kafka-connect.debezium.port") { debeziumContainer.getMappedPort(DEBEZIUM_PORT) }
      registry.add("kafka-connect.debezium.user") { POSTGRES_DEBEZIUM_USER }
      registry.add("kafka-connect.debezium.password") { POSTGRES_DEBEZIUM_PASSWORD }
      registry.add("kafka-connect.debezium.databaseHost") { POSTGRES_NETWORK_ALIAS }
      registry.add("kafka-connect.debezium.databaseName") { postgreSQLContainer.databaseName }
    }
  }
}
