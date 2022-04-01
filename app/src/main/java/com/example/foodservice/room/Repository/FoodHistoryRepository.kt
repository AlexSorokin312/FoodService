package com.example.foodservice.room.Repository

import com.example.foodservice.model.Dish
import com.example.foodservice.model.FoodCategory

interface FoodHistoryRepository {

    fun saveCategory(category: FoodCategory)
    fun getCategories(): List<FoodCategory>
    fun deleteCategories()
    fun getAllDishesFromCategories(category: String)
    fun saveDishesInCategory(categoryID: Int, dish: Dish)
    fun deleteAll()


}