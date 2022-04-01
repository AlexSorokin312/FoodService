package com.example.foodservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodservice.fragmentNavigation.INavigation
import com.example.foodservice.fragmentNavigation.MainRouter
import com.example.foodservice.databinding.ActivityMainBinding
import com.example.foodservice.view.MainScreenFragment

class MainActivity : AppCompatActivity(), INavigation {

    //region Properties
    private lateinit var binding: ActivityMainBinding
    private var mainRouter: MainRouter? = null
    //endregion


    //region ActivityMethods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainRouter = MainRouter(supportFragmentManager, binding.container.id)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            mainRouter?.navigateToFragment(MainScreenFragment.newInstance())
        }
    }


    override fun navigateToFragment(fragment: Fragment) {
        mainRouter?.navigateToFragment(fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainRouter = null
    }

    //endregion
}