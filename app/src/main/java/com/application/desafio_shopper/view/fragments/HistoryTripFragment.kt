package com.application.desafio_shopper.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.R
import com.application.desafio_shopper.adapter.HistoryAdapter
import com.application.desafio_shopper.databinding.HistoryTripFragmentBinding
import com.application.desafio_shopper.viewmodel.HistoryTripViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTripFragment : Fragment() {
    private lateinit var binding: HistoryTripFragmentBinding
    private val viewModel: HistoryTripViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoryTripFragmentBinding.inflate(inflater, container, false)

        setupView()
        setupObserver()

        return binding.root
    }

    private fun setupView() {
        val driverOptions = listOf(
            Pair(1, "Homer Simpson"),
            Pair(2, "Dominic Toretto"),
            Pair(3, "James Bond")
        )

        val driverOptionsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            driverOptions.map { it.second }
        )

        binding.filledExposedDropdown.setAdapter(driverOptionsAdapter)

        binding.buttonHistoryTrip.setOnClickListener {
            val selectedDriverName = binding.filledExposedDropdown.text.toString()
            val idCustomer = binding.edittextIdUserHistoryTrip.text.toString()
            val idDriver = driverOptions.firstOrNull { it.second == selectedDriverName }?.first
            viewModel.getCustomerHistory(idCustomer, idDriver.toString())
        }
    }

    private fun setupObserver() {
        val historyAdapter = HistoryAdapter(emptyList())

        binding.recyclerviewHistoryTrip.adapter = historyAdapter

        viewModel.error.observe(viewLifecycleOwner) {
            binding.textviewErrorHistoryTrip.visibility = View.VISIBLE
            binding.textviewErrorHistoryTrip.text = it.error_description
        }

        viewModel.responseCustomerHistory.observe(viewLifecycleOwner) {
            binding.textviewErrorHistoryTrip.visibility = View.GONE
            historyAdapter.updateRideList(it.rides)
        }
    }
}