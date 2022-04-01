package com.example.foodservice.foodAPI

import com.google.gson.annotations.SerializedName

data class FoodServerResponseData(
    @field:SerializedName("meals") val meals: List<DishDTO>
)

data class CategoryResponseData(
    @field:SerializedName("categories") val meals: List<FoodCategoryDTO>
)