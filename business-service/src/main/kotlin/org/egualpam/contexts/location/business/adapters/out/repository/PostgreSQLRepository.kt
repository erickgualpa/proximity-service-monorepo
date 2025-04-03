package org.egualpam.contexts.location.business.adapters.out.repository

import org.egualpam.contexts.location.business.application.domain.Business
import org.egualpam.contexts.location.business.application.ports.out.Repository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.UUID

class PostgreSQLRepository(
  private val jdbcTemplate: NamedParameterJdbcTemplate
) : Repository {

  override fun find(id: String): Business? {
    TODO()
  }

  override fun save(business: Business) {
    val sql =
        """
        INSERT INTO business (id, address, city, state, country, latitude, longitude)
        VALUES (:id, :address, :city, :state, :country, :latitude, :longitude)
        ON CONFLICT (id) DO UPDATE SET
          address = EXCLUDED.address,
          city = EXCLUDED.city,
          state = EXCLUDED.state,
          country = EXCLUDED.country,
          latitude = EXCLUDED.latitude,
          longitude = EXCLUDED.longitude
        """.trimIndent()

    val params = MapSqlParameterSource()
        .addValue("id", UUID.fromString(business.id()))
        .addValue("address", business.addressStreet())
        .addValue("city", business.addressCity())
        .addValue("state", business.addressState())
        .addValue("country", business.addressCountry())
        .addValue("latitude", business.locationLatitude())
        .addValue("longitude", business.locationLongitude())

    jdbcTemplate.update(sql, params)
  }
}
