package com.application.desafio_shopper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.desafio_shopper.databinding.DriverItemRecyclerViewBinding
import com.application.desafio_shopper.model.Option

class DriverAdapter(
    private var listDriver: List<Option>,
    private val onItemClicked: () -> Unit
) : RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateDriverList(newList: List<Option>) {
        listDriver = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DriverItemRecyclerViewBinding.inflate(inflater, parent, false)
        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = listDriver[position]
        holder.bind(driver)
    }

    override fun getItemCount(): Int {
        return listDriver.size
    }

    inner class DriverViewHolder(private val binding: DriverItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(driver: Option) {
            binding.textviewValueNameDriverItem.text = driver.name
            binding.textviewValueVehicleDriverItem.text = driver.vehicle
            binding.textviewValueDescriptionDriverItem.text = driver.description
            binding.textviewValueMoneyDriverItem.text = driver.value.toString()
            binding.textviewValueRatingDriverItem.text = driver.review.rating.toString()
            binding.buttonDriverItem.setOnClickListener {
                onItemClicked()
            }
        }
    }
}