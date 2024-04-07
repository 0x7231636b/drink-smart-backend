package com.github.x7231636b.drinksmartbackend.service

import DrinkData
import GetVolumeForDayResponse
import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity
import com.github.x7231636b.drinksmartbackend.repository.DrinkDetectionRepository
import com.github.x7231636b.drinksmartbackend.util.isSameDay
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
    var list =
        drinkDetectionRepository.findAllByUserName(userName).filter {
          isSameDay(it.timeStamp, dateToUse)
        }
    var sips = list.size
    var volume = list.map { it.volume }.sum()
    var response = GetVolumeForDayResponse(userName, dateToUse, volume, sips)
    return response
  }
}
