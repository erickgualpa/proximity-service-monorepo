package org.egualpam.contexts.location.business.adapters.out.searchrepository

import org.egualpam.contexts.location.business.application.ports.`in`.query.BusinessDto
import org.egualpam.contexts.location.business.application.ports.`in`.query.SearchResults
import org.egualpam.contexts.location.business.application.ports.out.SearchRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*

class PostgreSQLSearchRepository(
  private val jdbcTemplate: NamedParameterJdbcTemplate
) : SearchRepository {

  override fun find(id: String): BusinessDto? {
    val sql = """
            SELECT id, address, city, state, country, latitude, longitude
            FROM business
            WHERE id = :id
        """.trimIndent()

    val params = MapSqlParameterSource("id", UUID.fromString(id))

    return try {
      jdbcTemplate.queryForObject(sql, params, businessRowMapper)
          ?.let { business ->
            BusinessDto(
                id = business.id,
                address = business.address,
                city = business.city,
                state = business.state,
                country = business.country,
                latitude = business.latitude,
                longitude = business.longitude,
            )
          }
    } catch (e: EmptyResultDataAccessException) {
      null
    }
  }

  private val businessRowMapper = RowMapper { rs, _ ->
    BusinessPersistenceDto(
        id = rs.getString("id"),
        address = rs.getString("address"),
        city = rs.getString("city"),
        state = rs.getString("state"),
        country = rs.getString("country"),
        latitude = rs.getString("latitude"),
        longitude = rs.getString("longitude"),
    )
  }

  override fun search(): SearchResults {
    TODO("Not yet implemented")
  }

  data class BusinessPersistenceDto(
    val id: String,
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val latitude: String,
    val longitude: String
  )
}
