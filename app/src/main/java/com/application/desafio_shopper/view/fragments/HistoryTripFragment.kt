package com.application.desafio_shopper.view.fragments

import android.os.Bundle
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
        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        val historyAdapter = HistoryAdapter(emptyList())

        val driverOptionsAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.driver_options,
            android.R.layout.simple_dropdown_item_1line,
        )

        binding.buttonHistoryTrip.setOnClickListener {
            viewModel.getCustomerHistory("CT01", "1")
        }

        binding.filledExposedDropdown.setAdapter(driverOptionsAdapter)

        viewModel.responseCustomerHistory.observe(viewLifecycleOwner) {
            historyAdapter.updateRideList(it.rides)
        }

        binding.recyclerviewHistoryTrip.adapter = historyAdapter
    }
}