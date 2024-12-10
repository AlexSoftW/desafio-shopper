package com.application.desafio_shopper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.desafio_shopper.databinding.CarouselItemBinding
import com.application.desafio_shopper.model.CarouselItem
import com.bumptech.glide.Glide

class CarouselAdapter(private val carouselItems: List<CarouselItem>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val carouselItem = carouselItems[position]
        holder.bind(carouselItem)
    }

    override fun getItemCount(): Int {
        return carouselItems.size
    }

    inner class CarouselViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(carouselItem: CarouselItem) {
            binding.textviewTitleCarousel.text = carouselItem.title
            binding.textviewSubtitleCarousel.text = carouselItem.subtitle

            Glide.with(binding.imageviewCarousel.context)
                .load(carouselItem.image)
                .into(binding.imageviewCarousel)
        }
    }
}
