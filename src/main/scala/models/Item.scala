package models

final case class Item(
  cost: Double,
  shippingFee: Double,
  taxAmount: Double,
  product: Product
) {
  val price: Double = cost + (cost * shippingFee) + (cost * taxAmount)
}
