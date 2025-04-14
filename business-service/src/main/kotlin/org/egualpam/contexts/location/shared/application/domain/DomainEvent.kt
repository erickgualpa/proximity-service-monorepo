package org.egualpam.contexts.location.shared.application.domain

import java.util.UUID

data class DomainEventId(val value: UUID) {
  companion object {
    fun generate() = DomainEventId(UUID.randomUUID())
  }
}

data class EntityId(val value: String)

abstract class DomainEvent(
  private val id: DomainEventId,
  private val entityId: EntityId,
) {
  fun id() = id.value
  fun entityId() = entityId.value
}
