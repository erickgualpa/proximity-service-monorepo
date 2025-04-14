package org.egualpam.contexts.location.e2e.helper

import org.springframework.kafka.annotation.KafkaListener

class ConsumeDomainEvents {

  private val events = mutableListOf<String>()

  @KafkaListener(
      topics = ["public.event_store"],
      groupId = "business-service",
  )
  fun consume(message: String) {
    events.add(message)
  }

  fun all(): List<String> {
    return events.toList()
  }
}
