package com.example.foodservice.room.DAO

import androidx.room.*
import com.example.foodservice.room.Entities.FoodCategoryEntity

@Dao
interface FoodCategoryDAO {
    @Query("SELECT * FROM FoodCategoryEntity")
    fun all(): List<FoodCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FoodCategoryEntity)

    @Delete
    fun delete(entity: FoodCategoryEntity)

    @Query("DELETE FROM FoodCategoryEntity")
    fun deleteAll()
}