package com.example.foodservice.room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodservice.model.FoodCategory

@Entity
data class DishesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val categoryId: Int,
    val title: String,
    val img_src: String,
    val price: Int
)