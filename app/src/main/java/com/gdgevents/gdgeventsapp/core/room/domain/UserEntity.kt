package com.gdgevents.gdgeventsapp.core.room.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,
    // add remaining
)
