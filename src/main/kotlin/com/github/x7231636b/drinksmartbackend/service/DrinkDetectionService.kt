package com.github.x7231636b.drinksmartbackend.service

import com.github.x7231636b.drinksmartbackend.entity.DrinkData
import com.github.x7231636b.drinksmartbackend.repository.DrinkDetectionRepository
import org.springframework.stereotype.Service

@Service
class DrinkDetectionService(private val drinkDetectionRepository: DrinkDetectionRepository) {

  fun saveDrinkData(drinkData: DrinkData) {
    drinkDetectionRepository.save(drinkData)
  }

  fun getDrinkData(userName: String): List<DrinkData> {
    return drinkDetectionRepository.findAllByUserName(userName)
  }
}
