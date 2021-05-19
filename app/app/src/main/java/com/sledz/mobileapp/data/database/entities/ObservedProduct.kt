package com.sledz.mobileapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class ObservedProduct (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imageUrl: String,
    val name: String,
    val description: String,
    val price: Float,
)