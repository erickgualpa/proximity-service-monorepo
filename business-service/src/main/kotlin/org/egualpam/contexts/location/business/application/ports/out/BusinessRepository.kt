package org.egualpam.contexts.location.business.application.ports.out

import org.egualpam.contexts.location.business.application.domain.Business

interface BusinessRepository {
  fun find(id: String): Business?
  fun save(business: Business)
}
