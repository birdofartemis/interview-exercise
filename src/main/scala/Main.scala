import utils.Generators.getRandomOrders
import models.Order._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Main extends App {
  println("Example of a date: 2018-01-01 00:00:00")
  print("Start date: ")
  val startDate = StdIn.readLine()
  print("End date: ")
  val endDate = StdIn.readLine()

  val dates = Try {
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    (LocalDateTime.parse(startDate, dateFormat), LocalDateTime.parse(endDate, dateFormat))
  }

  dates match {
    case Success(dates) =>
      val orders         = getRandomOrders
      val filteredOrders = filterOrdersBetweenDates(dates._1, dates._2, orders)
      printOrdersByGroups(filteredOrders)

    case Failure(_) => println("Error -> Start date or end date don't respect the right format!")
  }
}
