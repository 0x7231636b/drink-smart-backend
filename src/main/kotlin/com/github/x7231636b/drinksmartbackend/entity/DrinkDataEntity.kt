package com.github.x7231636b.drinksmartbackend.entity

import DrinkData
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "drink_data_entity")
data class DrinkDataEntity(
        @Id @GeneratedValue val id: UUID? = null,
        val userName: String,
        val timeStamp: Long,
        val volume: Long,
) {
    companion object {
        fun fromDto(entity: DrinkData): DrinkDataEntity {
            return DrinkDataEntity(
                    entity.id?.let { UUID.fromString(it) },
                    entity.userName,
                    entity.timeStamp,
                    entity.volume
            )
        }
    }
}
