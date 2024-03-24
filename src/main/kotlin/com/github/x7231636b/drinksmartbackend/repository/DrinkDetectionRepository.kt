package com.github.x7231636b.drinksmartbackend.repository

import com.github.x7231636b.drinksmartbackend.entity.DrinkData
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DrinkDetectionRepository : CrudRepository<DrinkData, UUID> {
  fun findAllByUserName(userName: String): List<DrinkData>
}
