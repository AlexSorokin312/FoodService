package com.example.foodservice.room.DAO

import androidx.room.*
import com.example.foodservice.room.Entities.DishesEntity
import com.example.foodservice.room.Entities.FoodCategoryEntity

@Dao
interface DishesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FoodCategoryEntity)

    @Query("SELECT * FROM DishesEntity WHERE categoryId LIKE :categoryID")
    fun getDishesFromCategory(categoryID: String): List<DishesEntity>

    @Query("DELETE FROM FoodCategoryEntity")
    fun deleteAll()
}