package org.egualpam.contexts.location.e2e.helper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.kafka.annotation.KafkaListener
import java.time.Instant

class ConsumeDomainEvents(
  private val objectMapper: ObjectMapper
) {

  private val consumed = mutableListOf<DomainEvent>()

  @KafkaListener(
      topics = ["event_store.public.event_store"],
      groupId = "business-service",
  )
  fun consume(message: String) {
    val debeziumEvent = objectMapper.readValue<DebeziumEvent>(message)
    debeziumEvent.event.let {
      val domainEvent = objectMapper.readValue<DomainEvent>(it)
      consumed.add(domainEvent)
    }
  }

  fun all(): List<DomainEvent> {
    return consumed.toList()
  }

  data class DebeziumEvent(val event: String)

  data class DomainEvent(
    val id: String,
    val type: String,
    val version: String,
    val createdAt: Instant,
    val entityId: String,
    val data: Map<String, Any>,
  )
}
