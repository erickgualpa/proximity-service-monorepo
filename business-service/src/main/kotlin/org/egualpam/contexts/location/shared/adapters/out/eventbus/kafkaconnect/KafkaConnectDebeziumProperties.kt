package org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kafka-connect.debezium")
data class KafkaConnectDebeziumProperties(
  val host: String,
  val port: Int,
  val user: String,
  val password: String,
  val databaseHost: String,
  val databaseName: String
)
