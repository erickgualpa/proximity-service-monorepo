package org.egualpam.contexts.location.shared.adapters.out.eventbus.kafkaconnect

import com.fasterxml.jackson.databind.ObjectMapper
import org.egualpam.contexts.location.business.application.domain.BusinessCreated
import org.egualpam.contexts.location.shared.application.domain.DomainEvent
import org.egualpam.contexts.location.shared.application.ports.out.EventBus
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

class KafkaConnectEventBus(
  private val jdbcTemplate: NamedParameterJdbcTemplate,
  private val objectMapper: ObjectMapper,
) : EventBus {

  companion object {
    const val DEFAULT_VERSION = "1.0"
  }

  override fun publish(events: Set<DomainEvent>) {
    val sql =
        """
        INSERT INTO event_store (id, type, version, created_at, entity_id, event)
        VALUES (:id, :type, :version, :createdAt, :entityId, :event::jsonb)
        """.trimIndent()

    val sqlParams = MapSqlParameterSource()

    events.forEach {
      val eventId = it.id()
      val eventType = it::class.java.simpleName
      val createdAt = Instant.now()

      val entityId = UUID.fromString(it.entityId())

      sqlParams.addValue("id", eventId)
      sqlParams.addValue("type", eventType)
      sqlParams.addValue("createdAt", Timestamp.from(createdAt))
      sqlParams.addValue("entityId", entityId)

      if (it is BusinessCreated) {
        sqlParams.addValue("version", DEFAULT_VERSION)

        val eventData = mapOf(
            "addressStreet" to it.addressStreet,
            "addressCity" to it.addressCity,
            "addressState" to it.addressState,
            "addressCountry" to it.addressCountry,
            "locationLatitude" to it.locationLatitude,
            "locationLongitude" to it.locationLongitude,
        )

        val event = Event(
            id = eventId,
            type = eventType,
            version = DEFAULT_VERSION,
            createdAt = createdAt,
            entityId = entityId,
            data = eventData,
        )

        sqlParams.addValue("event", objectMapper.writeValueAsString(event))
      }

      jdbcTemplate.update(sql, sqlParams)
    }
  }

  data class Event(
    val id: UUID,
    val type: String,
    val version: String,
    val createdAt: Instant,
    val entityId: UUID,
    val data: Map<String, Any>,
  )
}
