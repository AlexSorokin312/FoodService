package com.example.foodservice.foodAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DishesAPI {

    @GET("categories.php")
    fun getCategories(): Call<CategoryResponseData>

    @GET("filter.php")
    fun getFoodInCategory(
        @Query("c") category: String
    ): Call<FoodServerResponseData>
}