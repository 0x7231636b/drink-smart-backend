package com.github.x7231636b.drinksmartbackend.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "drink_data")
data class DrinkData(
        @Id @GeneratedValue val id: UUID? = null,
        val userName: String,
        val timeStamp: Long,
        val volume: Long
)
