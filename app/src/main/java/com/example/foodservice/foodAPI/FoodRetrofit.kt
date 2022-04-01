package com.example.foodservice.foodAPI

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodRetrofit {

    private val baseUrl = " https://www.themealdb.com/api/json/v1/1/"

    fun getRetrofitImpl(): DishesAPI {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(DishesAPI::class.java)
    }
}


