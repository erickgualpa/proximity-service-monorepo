package org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.client.RestClient

class CreateEventStoreConnector(
  private val properties: KafkaConnectDebeziumProperties,
) : InitializingBean {

  private val logger: Logger = LoggerFactory.getLogger(this::class.java)

  companion object {
    private const val POSTGRESQL_PORT = 5432
  }

  override fun afterPropertiesSet() {
    val request = """
            {
              "name": "event-store-connector",
              "config": {
                "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
                "database.hostname": "${properties.databaseHost}",
                "database.port": "$POSTGRESQL_PORT",
                "database.user": "${properties.user}",
                "database.password": "${properties.password}",
                "database.dbname": "${properties.databaseName}",
                "database.server.name": "eventstore",
                "plugin.name": "pgoutput",
                "slot.name": "event_store_slot",
                "publication.autocreate.mode": "filtered",
                "table.include.list": "public.event_store",
                "topic.prefix": "event_store",
                "transforms": "unwrap",
                "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",
                "transforms.unwrap.drop.tombstones": "true",
                "key.converter": "org.apache.kafka.connect.json.JsonConverter",
                "value.converter": "org.apache.kafka.connect.json.JsonConverter",
                "key.converter.schemas.enable": "false",
                "value.converter.schemas.enable": "false"
              }
            }
          """.trimIndent()

    RestClient.create("http://${properties.host}:${properties.port}")
        .post()
        .uri("/connectors")
        .header("Content-Type", "application/json")
        .body(request)
        .retrieve()
        .onStatus(CREATED::equals) { _, _ ->
          logger.info("Successfully created debezium connector for event_store table")
        }
        .onStatus(CONFLICT::equals) { _, _ ->
          logger.warn("Debezium connector already exists")
        }
        .toBodilessEntity()
  }
}
