package models

import java.time.LocalDateTime

final case class Order(
  customerName: String,
  shippingAddress: String,
  totalPrice: Double,
  orderDate: LocalDateTime,
  items: List[Item],
  contact: String
)

object Order {
  def customerContact(name: String): String = name ++ "@gmail.com"
  val totalPrice: List[Item] => Double      = items => items.map(_.price).sum
}
