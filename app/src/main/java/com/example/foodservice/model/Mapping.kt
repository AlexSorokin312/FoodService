package com.example.foodservice.model

import com.example.foodservice.foodAPI.DishDTO
import com.example.foodservice.foodAPI.FoodCategoryDTO
import com.example.foodservice.room.Entities.FoodCategoryEntity

//region Server

fun convertDishDTOtoDish(dishDTO: DishDTO): Dish =
    Dish(dishDTO.strMeal, dishDTO.strMealThumb, (300..900).random())

fun convertFoodCategoryDTOtoFoodCategory(foodCategoryDTO: FoodCategoryDTO): FoodCategory =
    FoodCategory(foodCategoryDTO.strCategory)

//endregion

//region DB

fun convertFoodCategoryToEntity(category: FoodCategory): FoodCategoryEntity {
    return FoodCategoryEntity(0, category.title)
}

fun convertEntityToFoodCategory(entityList: List<FoodCategoryEntity>): List<FoodCategory> {
    return entityList.map {
        FoodCategory(it.category)
    }
}
//endregion
