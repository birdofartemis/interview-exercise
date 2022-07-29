package utils

import models.{Item, Order}
import resources.Data
import utils.DateHelpers.getRandomDateToOrder

import scala.annotation.tailrec
import scala.util.Random.between

object Generators extends Data {
  def getRandomElement[A](list: List[A]): A = list(between(0, list.size))

  def getRandomStreamOfItems: LazyList[Item] = {
    val streamSize = between(1, 75)
    @tailrec
    def constructList(size: Int, stream: LazyList[Item]): LazyList[Item] = {
      if (size == 0) stream
      else constructList(size - 1, stream.appended(itemsList(between(1, itemsList.size))))
    }
    constructList(streamSize, LazyList.empty[Item])
  }

  def getRandomOrders: LazyList[Order] = {
    val streamSize = 100

    @tailrec
    def constructList(size: Int, stream: LazyList[Order]): LazyList[Order] = {
      if (size == 0) stream
      else {
        val customer   = getRandomElement(namesList)
        val address    = getRandomElement(addressList)
        val items      = getRandomStreamOfItems
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
