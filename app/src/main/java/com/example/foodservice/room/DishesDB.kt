package com.example.foodservice.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodservice.room.DAO.DishesDAO
import com.example.foodservice.room.DAO.FoodCategoryDAO
import com.example.foodservice.room.Entities.DishesEntity
import com.example.foodservice.room.Entities.FoodCategoryEntity

@Database(
    entities = arrayOf(FoodCategoryEntity::class, DishesEntity::class),
    version = 1,
    exportSchema = true
)
abstract class DishesDB : RoomDatabase() {

    abstract fun foodCategoriesDAO(): FoodCategoryDAO
    abstract fun dishesDAO(): DishesDAO

}
