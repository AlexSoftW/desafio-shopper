package com.application.desafio_shopper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.desafio_shopper.databinding.HistoryItemRecyclerViewBinding
import com.application.desafio_shopper.model.Ride

class HistoryAdapter(private var listRide: List<Ride>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateRideList(newList: List<Ride>) {
        listRide = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemRecyclerViewBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val ride = listRide[position]
        holder.bind(ride)
    }

    override fun getItemCount(): Int {
        return listRide.size
    }

    inner class HistoryViewHolder(private val binding: HistoryItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ride: Ride) {
            binding.textviewValueNameHistoryItem.text = ride.driver.name
            binding.textviewValueStartAddressHistoryItem.text = ride.origin
            binding.textviewValueFinalAddressHistoryItem.text = ride.destination
            binding.textviewValueDistanceHistoryItem.text = ride.distance.toString()
            binding.textviewValueTimeHistoryItem.text = ride.duration
            binding.textviewDateHistoryItem.text = ride.date.toString()
            binding.textviewValueMoneyHistoryItem.text = ride.value.toString()
        }
    }
}