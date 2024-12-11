package com.application.desafio_shopper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.desafio_shopper.databinding.HistoryItemRecyclerViewBinding
import com.application.desafio_shopper.model.Ride
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bind(ride: Ride) {
            binding.textviewValueNameHistoryItem.text = ride.driver.name
            binding.textviewValueOriginAddressHistoryItem.text = ride.origin
            binding.textviewValueDestinationAddressHistoryItem.text = ride.destination
            binding.textviewValueDistanceHistoryItem.text = "%.2f km".format(ride.distance)
            binding.textviewValueTimeHistoryItem.text = ride.duration
            binding.textviewDateHistoryItem.text = formatDate(ride.date)
            binding.textviewValueMoneyHistoryItem.text = "%.2f".format(ride.value)
        }

        private fun formatDate(date: String): String {
            val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            val dateTime = LocalDateTime.parse(date, inputFormatter)
            val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")
            return dateTime.format(outputFormatter)
        }
    }
}