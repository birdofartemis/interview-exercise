package utils

import models.{Item, Order}
import resources.Data

import java.time.{LocalDate, LocalDateTime, LocalTime, Month}
import scala.annotation.tailrec
import scala.util.Random.between

object Generators extends Data {
  def getRandomDate: LocalDateTime =
    LocalDateTime
      .of(
        LocalDate.of(between(2015, 2022), Month.of(between(1, 13)), between(1, 25)),
        LocalTime.of(between(0, 24), between(0, 60), between(0, 60))
      )

  def getRandomDateToOrder(itemsList: List[Item]): LocalDateTime = {
    @tailrec
    def mostRecentProduct(currentItem: Item, next: List[Item]): Item = {
      if (next.isEmpty) currentItem
      else {
        if (currentItem.product.creationDate isAfter next.head.product.creationDate)
          mostRecentProduct(currentItem, next.tail)
        else mostRecentProduct(next.head, next.tail)
      }
    }
    val mostRecentDate = mostRecentProduct(itemsList.head, itemsList.tail).product.creationDate

    LocalDateTime
      .of(
        LocalDate.of(
          between(mostRecentDate.getYear, 2022),
          Month.of(between(mostRecentDate.getMonthValue, 13)),
          between(mostRecentDate.getDayOfMonth, 25)
        ),
        LocalTime.of(
          between(mostRecentDate.getHour, 24),
          between(mostRecentDate.getMinute, 60),
          between(mostRecentDate.getSecond, 60)
        )
      )

  }

  def getRandomItemList: List[Item] = {
    val listSize = between(1, 75)
    @tailrec
    def constructList(size: Int, list: List[Item]): List[Item] = {
      if (size == 0) list
      else constructList(size - 1, list.appended(itemsList(between(1, itemsList.size))))
    }
    constructList(listSize, List.empty[Item])
  }

  def getRandomCustomer: String = namesList(between(0, namesList.size))
  def getRandomAddress: String  = addressList(between(0, addressList.size))

  def getRandomOrders: LazyList[Order] = {
    val streamSize = 1

    @tailrec
    def constructList(size: Int, stream: LazyList[Order]): LazyList[Order] = {
      if (size == 0) stream
      else {
        val customer   = getRandomCustomer
        val address    = getRandomAddress
        val items      = getRandomItemList
        val totalPrice = Order.totalPrice(items)
        val orderDate  = getRandomDateToOrder(items)
        val contact    = Order.customerContact(customer)

        val order = Order(customer, address, totalPrice, orderDate, items, contact)

        constructList(size - 1, stream.appended(order))
      }
    }
    constructList(streamSize, LazyList.empty[Order])
  }
}
