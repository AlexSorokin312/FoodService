package com.example.foodservice.room.Repository

import com.example.foodservice.model.Dish
import com.example.foodservice.model.FoodCategory
import com.example.foodservice.model.convertEntityToFoodCategory
import com.example.foodservice.model.convertFoodCategoryToEntity
import com.example.foodservice.room.DAO.FoodCategoryDAO

class FoodHistoryRepositoryImp(
    private val foodCategoryDAO: FoodCategoryDAO,
) : FoodHistoryRepository {

    override fun saveCategory(category: FoodCategory) {
        foodCategoryDAO.insert(convertFoodCategoryToEntity(category))
    }

    override fun getCategories(): List<FoodCategory> {
        return convertEntityToFoodCategory(foodCategoryDAO.all())
    }

    override fun deleteCategories() {
        foodCategoryDAO.deleteAll()
    }

    override fun deleteAll() {
        foodCategoryDAO.deleteAll()
    }

    override fun getAllDishesFromCategories(category: String) {
        TODO("Not yet implemented")
    }

    override fun saveDishesInCategory(categoryID: Int, dish: Dish) {
        TODO("Not yet implemented")
    }
}