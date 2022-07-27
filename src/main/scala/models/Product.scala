package models

import java.time.LocalDateTime

final case class Product(
  name: String,
  category: String,
  weight: Double,
  price: Double,
  creationDate: LocalDateTime
)
