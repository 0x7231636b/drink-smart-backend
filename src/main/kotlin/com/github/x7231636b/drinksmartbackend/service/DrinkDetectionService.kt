package com.github.x7231636b.drinksmartbackend.service

import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity
import com.github.x7231636b.drinksmartbackend.repository.DrinkDetectionRepository
import com.github.x7231636b.drinksmartbackend.util.isSameDay
import org.springframework.stereotype.Service

@Service
class DrinkDetectionService(private val drinkDetectionRepository: DrinkDetectionRepository) {

  fun saveDrinkDataEntity(DrinkDataEntity: DrinkDataEntity) {
    drinkDetectionRepository.save(DrinkDataEntity)
  }

  fun getDrinkDataEntity(userName: String): List<DrinkDataEntity> {
    return drinkDetectionRepository.findAllByUserName(userName)
  }

  fun getDrinkDataEntity(userName: String, date: Long): List<DrinkDataEntity> {
    return drinkDetectionRepository.findAllByUserName(userName).filter {
      isSameDay(it.timeStamp, date)
    }
  }
}
