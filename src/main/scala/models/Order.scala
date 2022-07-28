package models

import utils.DateHelpers.{isBetween, subtractDates}

import java.time.LocalDateTime
import scala.annotation.tailrec

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

  def getOrderInterval(
    startDate: LocalDateTime,
    endDate: LocalDateTime,
    orders: LazyList[Order]
  ): LazyList[Order] = {
    orders.filter(order => isBetween(startDate, endDate, order.orderDate))
  }

  def getProductAgeWhenSold(orderDate: LocalDateTime, items: List[Item]): List[LocalDateTime] = {
    val listProductsAge = for {
      item <- items
      product    = item.product
      productAge = product.creationDate
    } yield productAge

    listProductsAge.map(productAge => subtractDates(orderDate, productAge))
  }

  def ordersByGroup(orderList: LazyList[Order], group: (Int, Int)): Int = {
    @tailrec
    def helper(
      currentItem: LocalDateTime,
      nextItem: List[LocalDateTime],
      validator: Boolean,
      accumulator: Int
    ): Int = {
      if (nextItem.isEmpty || !validator) accumulator
      else if (
        validator && currentItem.getMonthValue >= group._1 && currentItem.getMonthValue <= group._2 && currentItem.getYear == 0
      ) {
        helper(nextItem.head, nextItem.tail, false, accumulator + 1)
      } else helper(nextItem.head, nextItem.tail, true, accumulator)
    }
    val dates = orderList.map(order => getProductAgeWhenSold(order.orderDate, order.items))
    dates.map(item => helper(item.head, item.tail, true, 0)).sum
  }
}
