// @file:JvmName("DateUtils")
package com.github.x7231636b.drinksmartbackend.util

import java.util.*

fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
  val date1 = Date(timestamp1)
  val date2 = Date(timestamp2)

  val cal1 = Calendar.getInstance()
  cal1.time = date1

  val cal2 = Calendar.getInstance()
  cal2.time = date2

  return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
      cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun isSameMonth(timestamp1: Long, timestamp2: Long): Boolean {
  val date1 = Date(timestamp1)
  val date2 = Date(timestamp2)

  val cal1 = Calendar.getInstance()
  cal1.time = date1

  val cal2 = Calendar.getInstance()
  cal2.time = date2

  return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
      cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
}

fun getStartOfDay(timestamp: Long): Long {
  val date = Date(timestamp)
  val cal = Calendar.getInstance()
  cal.time = date

  cal.set(Calendar.HOUR_OF_DAY, 0)
  cal.set(Calendar.MINUTE, 0)
  cal.set(Calendar.SECOND, 0)
  cal.set(Calendar.MILLISECOND, 0)

  return cal.timeInMillis
}

fun getEndOfDay(timestamp: Long): Long {
  val date = Date(timestamp)
  val cal = Calendar.getInstance()
  cal.time = date

  cal.set(Calendar.HOUR_OF_DAY, 23)
  cal.set(Calendar.MINUTE, 59)
  cal.set(Calendar.SECOND, 59)
  cal.set(Calendar.MILLISECOND, 999)

  return cal.timeInMillis
}

fun getStartOfMonth(timestamp: Long): Long {
  val date = Date(timestamp)
  val cal = Calendar.getInstance()
  cal.time = date

  cal.set(Calendar.DAY_OF_MONTH, 1)
  cal.set(Calendar.HOUR_OF_DAY, 0)
  cal.set(Calendar.MINUTE, 0)
  cal.set(Calendar.SECOND, 0)
  cal.set(Calendar.MILLISECOND, 0)

  return cal.timeInMillis
}

fun getEndOfMonth(timestamp: Long): Long {
  val date = Date(timestamp)
  val cal = Calendar.getInstance()
  cal.time = date

  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
  cal.set(Calendar.HOUR_OF_DAY, 23)
  cal.set(Calendar.MINUTE, 59)
  cal.set(Calendar.SECOND, 59)
  cal.set(Calendar.MILLISECOND, 999)

  return cal.timeInMillis
}
