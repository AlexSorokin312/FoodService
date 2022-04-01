package com.example.foodservice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodservice.databinding.DishItemBinding
import com.example.foodservice.model.Dish
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

class DishesAdapter : RecyclerView.Adapter<DishesAdapter.DishViewHolder>() {

    //region Adapter

    private var itemListener: MainScreenFragment.OnDishItemFoodClickListener? = null
    private var dishes: List<Dish> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = DishItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) =
        holder.bind(dishes[position])

    override fun getItemCount(): Int = dishes.size

    fun setData(data: List<Dish>) {
        dishes = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: MainScreenFragment.OnDishItemFoodClickListener) {
        itemListener = listener
    }

    //endregion


    //region ViewHolder

    inner class DishViewHolder(binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imageView: ImageView = binding.dishImage
        private val titleView: TextView = binding.dishTitle
        private val dishPrice: Chip = binding.dishPrice
        private val item: View = binding.root

        fun bind(dish: Dish) {
            titleView.text = dish.title
            dishPrice.text = "от ${dish.price} руб"
            val url = String.format(
                "${dish.imageUrl}"
            )
            Picasso
                .get()
                .load(url)
                .into(imageView);
            item.setOnClickListener {
                itemListener?.onDishItemFoodClickListener(dish)
            }
        }
    }

    //endregion
}