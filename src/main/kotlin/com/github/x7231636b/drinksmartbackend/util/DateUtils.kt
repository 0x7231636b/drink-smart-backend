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
