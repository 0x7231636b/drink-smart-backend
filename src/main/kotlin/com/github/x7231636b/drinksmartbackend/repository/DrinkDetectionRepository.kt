package com.github.x7231636b.drinksmartbackend.repository

import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DrinkDetectionRepository : CrudRepository<DrinkDataEntity, UUID> {
  fun findAllByUserName(userName: String): List<DrinkDataEntity>

  fun findAllByUserNameAndTimeStampBetween(
      userName: String,
      start: Long,
      end: Long
  ): List<DrinkDataEntity>
}
