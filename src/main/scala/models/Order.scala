package models
import models.Product.getProductAgeWhenSold
import utils.DateHelpers.{isBetween, isPartOfGroup}

import java.time.LocalDateTime
import scala.annotation.tailrec

final case class Order(
  customerName: String,
  shippingAddress: String,
  totalPrice: Double,
  orderDate: LocalDateTime,
  items: LazyList[Item],
  contact: String
)
object Order {
  def customerContact(name: String): String = name ++ "@gmail.com"

  val totalPrice: LazyList[Item] => Double = items => items.map(_.cost).sum

  def printOrdersByGroups(orderList: LazyList[Order]): Unit = {
    println(s"1-3 months -> ${ordersByGroup(orderList, (1, 3), false)}")
    println(s"4-6 months -> ${ordersByGroup(orderList, (4, 6), false)}")
    println(s"7-12 months -> ${ordersByGroup(orderList, (7, 12), false)}")
    println(s">12 months -> ${ordersByGroup(orderList, (0, 0), true)}")
  }
  def ordersByGroup(orderList: LazyList[Order], group: (Int, Int), hasYears: Boolean): Int = {
    @tailrec
    def helper(
      currentItemAge: LocalDateTime,
      nextItemAge: LazyList[LocalDateTime],
      isValid: Boolean,
      hasYears: Boolean,
      accumulator: Int
    ): Int = {
      if (nextItemAge.isEmpty || !isValid) accumulator
      else if (isValid && isPartOfGroup(currentItemAge, hasYears, group))
        helper(nextItemAge.head, nextItemAge.tail, false, hasYears, accumulator + 1)
      else helper(nextItemAge.head, nextItemAge.tail, true, hasYears, accumulator)
    }
    val dates = orderList.map(order => getProductAgeWhenSold(order.orderDate, order.items))
    dates.map(item => helper(item.head, item.tail, true, hasYears, 0)).sum
  }

  def filterOrdersBetweenDates(
    from: LocalDateTime,
    to: LocalDateTime,
    orders: LazyList[Order]
  ): LazyList[Order] = {
    if (from isAfter to) orders.filter(order => isBetween(from, to, order.orderDate))
    else orders.filter(order => isBetween(to, from, order.orderDate))
  }

}
