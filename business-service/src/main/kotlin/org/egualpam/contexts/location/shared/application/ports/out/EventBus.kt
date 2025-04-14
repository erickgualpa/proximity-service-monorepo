package org.egualpam.contexts.location.shared.application.ports.out

import org.egualpam.contexts.location.shared.application.domain.DomainEvent

interface EventBus {
  fun publish(events: Set<DomainEvent>)
}
