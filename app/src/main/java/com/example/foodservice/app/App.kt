package com.example.foodservice.app

import android.app.Application
import androidx.room.Room
import com.example.foodservice.room.DAO.DishesDAO
import com.example.foodservice.room.DAO.FoodCategoryDAO
import com.example.foodservice.room.DishesDB


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: DishesDB? = null
        private const val DB_NAME = "History.db"


        fun getFoodCategoryDAO(): FoodCategoryDAO {
            if (db == null) {
                synchronized(DishesDB::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Error")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DishesDB::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.foodCategoriesDAO()
        }

        fun getDishesCategoryDAO(): DishesDAO {
            if (db == null) {
                synchronized(DishesDB::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Error")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DishesDB::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.dishesDAO()
        }
    }
}