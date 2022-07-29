package utils

import models.Item

import java.time.{LocalDate, LocalDateTime, LocalTime, Month}
import scala.annotation.tailrec
import scala.util.Random.between

object DateHelpers {
  def isPartOfGroup(itemAge: LocalDateTime, hasYears: Boolean, group: (Int, Int)): Boolean =
    if (!hasYears)
      itemAge.getMonthValue >= group._1 && itemAge.getMonthValue <= group._2 && itemAge.getYear == 0
    else itemAge.getYear > 0

  def subtractDates(orderDate: LocalDateTime, productCreationDate: LocalDateTime): LocalDateTime = {
    orderDate
      .minusDays(productCreationDate.getDayOfMonth)
      .minusMonths(productCreationDate.getMonthValue)
      .minusYears(productCreationDate.getYear)
  }
  def isBetween(fromDate: LocalDateTime, toDate: LocalDateTime, orderDate: LocalDateTime): Boolean =
    (orderDate.isEqual(toDate) || orderDate.isAfter(toDate)) && (orderDate.isBefore(fromDate) || orderDate
      .isEqual(fromDate))

  def getRandomDate: LocalDateTime =
    LocalDateTime
      .of(
        LocalDate.of(between(2015, 2022), Month.of(between(1, 13)), between(1, 25)),
        LocalTime.of(between(0, 24), between(0, 60), between(0, 60))
      )

  def getRandomDateToOrder(itemsList: LazyList[Item]): LocalDateTime = {
    @tailrec
    def mostRecentProduct(currentItem: Item, next: LazyList[Item]): Item = {
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
}
