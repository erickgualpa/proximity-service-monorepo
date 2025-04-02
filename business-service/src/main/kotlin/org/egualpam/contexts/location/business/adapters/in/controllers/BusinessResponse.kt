package org.egualpam.contexts.location.business.adapters.`in`.controllers

data class BusinessResponse(
  val id: String,
  val address: String,
  val city: String,
  val state: String,
  val country: String,
  val latitude: String,
  val longitude: String
) {
}
