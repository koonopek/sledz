package com.sledz.mobileapp.data.database.entities

import androidx.room.Entity

@Entity
data class Price(
    val value: Double,
    val date: Long)