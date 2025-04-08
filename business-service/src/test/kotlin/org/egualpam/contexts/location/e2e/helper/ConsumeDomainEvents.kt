package org.egualpam.contexts.location.e2e.helper

import java.util.UUID.randomUUID

class ConsumeDomainEvents {
  fun from(topicName: String): List<String> {
    val event = """
    {
      "id": "${randomUUID()}",
    }
    """.trimIndent()
    return listOf(event)
  }
}
