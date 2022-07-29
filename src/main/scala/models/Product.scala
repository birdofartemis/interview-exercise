package models

import utils.DateHelpers.subtractDates

import java.time.LocalDateTime

final case class Product(
  name: String,
  category: String,
  weight: Double,
  price: Double,
  creationDate: LocalDateTime
)
object Product {
  def getProductAgeWhenSold(orderDate: LocalDateTime, items: LazyList[Item]): LazyList[LocalDateTime] = {
    val listProductsAge = for {
      item <- items
      product    = item.product
      productAge = product.creationDate
    } yield productAge

    listProductsAge.map(productAge => subtractDates(orderDate, productAge))
  }
}
