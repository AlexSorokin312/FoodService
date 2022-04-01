package com.example.foodservice.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodservice.R
import com.example.foodservice.databinding.FragmentMainBinding
import com.example.foodservice.model.Dish
import com.example.foodservice.model.FoodCategory
import com.example.foodservice.viewModel.AppState
import com.example.foodservice.viewModel.FragmentMainViewModel
import com.google.android.material.chip.Chip


class MainScreenFragment : Fragment() {

    interface OnDishItemFoodClickListener {
        fun onDishItemFoodClickListener(dish: Dish)
    }

    //region Properties

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: FragmentMainViewModel by lazy {
        ViewModelProvider(this).get(FragmentMainViewModel::class.java)
    }

    //ImagesAdapter - адаптер намеренно сделан с хард-кодом картинок - для вывода верхних банеров
    private var imagesAdapter: ImagesAdapter? = ImagesAdapter()

    private var wordsAdapter: DishesAdapter? = DishesAdapter().apply {
        setOnItemClickListener(object : OnDishItemFoodClickListener {

            override fun onDishItemFoodClickListener(dish: Dish) {
                Toast.makeText(requireContext(), dish.title, Toast.LENGTH_LONG).show()
            }
        })
    }


    //endregion

    //region Fragment Methods

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomMenuMenu()
        val observer = Observer<AppState>() { renderData(it) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getFoodCategoriesFromServer()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessFoodCategoriesLoading -> {
                val categories = appState.categories
                setChips(categories)
                viewModel.getDishesFromCategoryFromServer(categories[0].title)
            }
            is AppState.SuccessDishesFromCategoryLoaded -> {
                val dishes = appState.dishes
                wordsAdapter?.setData(dishes)
                imagesAdapter?.setData(listOf(R.drawable.stock_4, R.drawable.stock_4))
                binding.imagesRecyclerView.adapter = imagesAdapter
                binding.recyclerView.adapter = wordsAdapter
            }
            is AppState.Loading -> {
                //todo loading
            }
            is AppState.Error -> {
                //todo error
            }
        }
    }

    companion object {
        fun newInstance() = MainScreenFragment()
    }
//endregion Fragment Methods

    //region initView
    private fun setBottomMenuMenu() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    Toast.makeText(requireContext(), "menu", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.profile -> {
                    Toast.makeText(requireContext(), "profile", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.basket -> {
                    Toast.makeText(requireContext(), "basket", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setChips(categories: List<FoodCategory>) {
        categories.forEach {
            val category = it
            val name = it.title
            val chip =
                layoutInflater.inflate(R.layout.chip_category, binding.chipGroup, false) as Chip
            chip.text = name
            chip.setOnClickListener {
                viewModel.getDishesFromCategoryFromServer(name)
            }
            binding.chipGroup.addView(chip)
        }
    }
    //endregion

}