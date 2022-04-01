package com.example.foodservice.room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val category: String,
)