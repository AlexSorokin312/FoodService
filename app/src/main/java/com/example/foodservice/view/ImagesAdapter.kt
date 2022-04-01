package com.example.foodservice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodservice.R
import com.example.foodservice.databinding.ImageItemBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    //region Adapter

    private var images: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind()

    override fun getItemCount(): Int = images.size

    fun setData(data: List<Int>) {
        images = data
        notifyDataSetChanged()
    }

    //endregion


    //region ViewHolder

    class ImageViewHolder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imageBanner: ImageView = binding.imageBanner

        fun bind() {
            imageBanner.setBackgroundResource(R.drawable.stock_4)
        }
    }

    //endregion
}