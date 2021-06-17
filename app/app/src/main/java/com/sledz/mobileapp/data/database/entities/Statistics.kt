package com.sledz.mobileapp.data.database.entities

import androidx.room.Entity

@Entity
data class Statistics (
    val min: Double,
    val std: Double,
    val avg: Double
        )