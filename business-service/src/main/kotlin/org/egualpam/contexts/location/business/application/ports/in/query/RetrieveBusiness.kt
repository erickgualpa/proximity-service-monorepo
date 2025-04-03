package org.egualpam.contexts.location.business.application.ports.`in`.query

import org.egualpam.contexts.location.business.application.ports.out.SearchRepository

class RetrieveBusiness(
  private val searchRepository: SearchRepository
) {

  fun execute(query: RetrieveBusinessQuery): BusinessDto {
    return query.id.let {
      searchRepository.find(it)
    }
  }
}

data class RetrieveBusinessQuery(val id: String)

data class SearchResults(
  val results: List<BusinessDto>
)

data class BusinessDto(
  val id: String,
  val address: String,
  val city: String,
  val state: String,
  val country: String,
  val latitude: String,
  val longitude: String
)
