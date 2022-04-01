package com.example.foodservice.viewModel

import com.example.foodservice.model.Dish
import com.example.foodservice.model.FoodCategory

sealed class AppState {

    data class SuccessFoodCategoriesLoading(val categories: List<FoodCategory>) : AppState()
    data class SuccessDishesFromCategoryLoaded(val dishes: List<Dish>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}
