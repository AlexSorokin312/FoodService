package com.example.foodservice.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodservice.app.App
import com.example.foodservice.foodAPI.CategoryResponseData
import com.example.foodservice.foodAPI.FoodRetrofit
import com.example.foodservice.foodAPI.FoodServerResponseData
import com.example.foodservice.model.Dish
import com.example.foodservice.model.FoodCategory
import com.example.foodservice.model.convertDishDTOtoDish
import com.example.foodservice.model.convertFoodCategoryDTOtoFoodCategory
import com.example.foodservice.room.Repository.FoodHistoryRepository
import com.example.foodservice.room.Repository.FoodHistoryRepositoryImp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMainViewModel(
    private val retrofitImp: FoodRetrofit = FoodRetrofit(),
    private val observableData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    private val historyRepository: FoodHistoryRepository = FoodHistoryRepositoryImp(
        App.getFoodCategoryDAO()
    )
) : ViewModel() {

    //region CallBacks
    private val gettingDishesCallBack = object : Callback<FoodServerResponseData> {
        override fun onResponse(
            call: Call<FoodServerResponseData>,
            response: Response<FoodServerResponseData>
        ) {
            val response = response.body()
            if (response != null) {
                val dishes = handleDishesResponse(response)
                observableData.postValue(AppState.SuccessDishesFromCategoryLoaded(dishes))
            } else {
                handleNoConnection()
            }
        }

        override fun onFailure(call: Call<FoodServerResponseData>, t: Throwable) {
            handleNoConnection()
        }
    }

    private val gettingFoodCategoriesCallBack = object : Callback<CategoryResponseData> {
        override fun onResponse(
            call: Call<CategoryResponseData>,
            response: Response<CategoryResponseData>
        ) {
            val response = response.body()
            if (response != null) {
                val categories = handleFoodCategoryResponse(response)
                observableData.postValue(AppState.SuccessFoodCategoriesLoading(categories))
            } else {
                handleNoConnection()
            }
        }

        override fun onFailure(call: Call<CategoryResponseData>, t: Throwable) {
            handleNoConnection()
        }
    }
    //endregion

    fun getData() = observableData


    //region DBWork
    private fun getFoodCategoriesFromLocalSource() {
        Thread {
            historyRepository.getCategories()
        }.start()
    }

    private fun saveCategoriesToLocalDataBase(categories: List<FoodCategory>) {
        Thread {
            historyRepository.deleteAll()
            categories.forEach {
                historyRepository.saveCategory(it)
                val d = historyRepository.getCategories()
            }
        }.start()
    }
    //endregion


    //region Server
    fun getDishesFromCategoryFromServer(category: String) {
        observableData.postValue(AppState.Loading)
        Thread {
            retrofitImp.getRetrofitImpl().getFoodInCategory(category).enqueue(gettingDishesCallBack)
        }.start()
    }

    fun getFoodCategoriesFromServer() {
        observableData.postValue(AppState.Loading)
        Thread {
            retrofitImp.getRetrofitImpl().getCategories().enqueue(gettingFoodCategoriesCallBack)
        }.start()
    }
    //endregion


    //Region Responses Handlers
    private fun handleDishesResponse(foodDTO: FoodServerResponseData): List<Dish> {
        val dishesList = arrayListOf<Dish>()
        foodDTO.meals.forEach {
            dishesList.add(convertDishDTOtoDish(it))
        }
        return dishesList
    }

    private fun handleFoodCategoryResponse(foodDTO: CategoryResponseData): List<FoodCategory> {
        val foodCategoryList = arrayListOf<FoodCategory>()
        foodDTO.meals.forEach {
            val foodCategory = convertFoodCategoryDTOtoFoodCategory(it)
            foodCategoryList.add(foodCategory)
        }
        saveCategoriesToLocalDataBase(foodCategoryList)
        return foodCategoryList
    }

    private fun handleNoConnection() {
        val categories = historyRepository.getCategories()
        observableData.postValue(AppState.SuccessFoodCategoriesLoading(categories))
    }
    //endregion
}
