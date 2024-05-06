package com.github.x7231636b.drinksmartbackend.service

import DrinkData
import GetVolumeForDayResponse
import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity
import com.github.x7231636b.drinksmartbackend.repository.DrinkDetectionRepository
import com.github.x7231636b.drinksmartbackend.util.getEndOfDay
import com.github.x7231636b.drinksmartbackend.util.getEndOfMonth
import com.github.x7231636b.drinksmartbackend.util.getStartOfDay
import com.github.x7231636b.drinksmartbackend.util.getStartOfMonth
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DrinkDetectionService(private val drinkDetectionRepository: DrinkDetectionRepository) {

  private val log: Logger = LoggerFactory.getLogger(DrinkDetectionService::class.java)

  fun saveDrinkData(drinkData: DrinkData) {
    drinkDetectionRepository.save(DrinkDataEntity.fromDto(drinkData))
  }

  fun updateDrinkData(drinkData: DrinkData) {
    drinkDetectionRepository.findById(UUID.fromString(drinkData.id)).ifPresent { existingEntity ->
      val updatedEntity =
          existingEntity.copy(
              id = existingEntity.id,
              userName = drinkData.userName,
              timeStamp = drinkData.timeStamp,
              volume = drinkData.volume
          )
      drinkDetectionRepository.save(updatedEntity)
    }
  }

  fun getDrinkData(userName: String): List<DrinkDataEntity> {
    return drinkDetectionRepository.findAllByUserName(userName)
  }

  fun getVolumeForDay(userName: String, date: Long?): GetVolumeForDayResponse {
    val dateToUse = date ?: System.currentTimeMillis()

    val start = getStartOfDay(dateToUse)
    val end = getEndOfDay(dateToUse)

    var list = drinkDetectionRepository.findAllByUserNameAndTimeStampBetween(userName, start, end)
    var sips = list.size
    var volume = list.fold(0) { sum, it -> sum + it.volume }

    var response = GetVolumeForDayResponse(userName, dateToUse, volume, sips)
    return response
  }

  fun getVolumesForMonth(userName: String, date: Long?): List<GetVolumeForDayResponse> {
    val dateToUse = date ?: System.currentTimeMillis()

    val start = getStartOfMonth(dateToUse)
    val end = getEndOfMonth(dateToUse)

    val monthlyEntries =
        drinkDetectionRepository.findAllByUserNameAndTimeStampBetween(userName, start, end)

    val responseList = mutableListOf<GetVolumeForDayResponse>()
    for (i in 1..31) {
      val loopDayOffset = start + i * 24 * 60 * 60 * 1000
      val dayStart = getStartOfDay(loopDayOffset)
      val dayEnd = getEndOfDay(loopDayOffset)

      val dayList = monthlyEntries.filter { it.timeStamp in dayStart..dayEnd }

      if (dayList.isEmpty()) continue

      val sips = dayList.size
      val volume = dayList.fold(0) { sum, it -> sum + it.volume }

      val middleOfDay = (dayStart + dayEnd) / 2
      val volumeForDay = GetVolumeForDayResponse(userName, middleOfDay, volume, sips)
      responseList.add(volumeForDay)
    }

    return responseList
  }
}
