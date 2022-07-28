package utils

import java.time.LocalDateTime

object DateHelpers {
  def isBetween(startDate: LocalDateTime, endDate: LocalDateTime, date: LocalDateTime): Boolean =
    (date.isEqual(startDate) || date.isAfter(startDate)) && date.isBefore(endDate)

  def subtractDates(date1: LocalDateTime, date2: LocalDateTime): LocalDateTime = {
    date1.minusDays(date2.getDayOfMonth).minusMonths(date2.getMonthValue).minusYears(date2.getYear)
  }

}
