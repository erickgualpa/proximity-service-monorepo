package org.egualpam.contexts.location.business.application.ports.out

import org.egualpam.contexts.location.business.application.ports.`in`.query.BusinessDto
import org.egualpam.contexts.location.business.application.ports.`in`.query.SearchResults

interface BusinessSearchRepository {
  fun find(id: String): BusinessDto?
  fun search(): SearchResults
}
