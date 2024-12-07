package com.application.desafio_shopper.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.R
import com.application.desafio_shopper.adapter.DriverAdapter
import com.application.desafio_shopper.databinding.ChooseTripFragmentBinding
import com.application.desafio_shopper.viewmodel.ChooseTripViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseTripFragment : Fragment() {
    private lateinit var binding: ChooseTripFragmentBinding
    private val viewModel: ChooseTripViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChooseTripFragmentBinding.inflate(inflater, container, false)
        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.getAllTripViewModel()
        viewModel.getAllDriverViewModel()

        val driveAdapter = DriverAdapter(emptyList()) {
            fragmentReplaceManager(HistoryTripFragment())
        }

        viewModel.listDriver.observe(viewLifecycleOwner, { driveList ->
            driveAdapter.updateDriverList(driveList)
        })

        binding.recyclerviewChooseTrip.adapter = driveAdapter

        viewModel.listTrip.observe(viewLifecycleOwner) {
            val trip = it[0]

            binding.textviewValueStartAddressChooseTrip.text = trip.duration
            binding.textviewValueFinalAddressChooseTrip.text = trip.options[0].description

            val mapUrl = "https://maps.googleapis.com/maps/api/staticmap?size=400x200" +
                    "&markers=color:red|${trip.origin.latitude},${trip.origin.longitude}" +
                    "&markers=color:blue|${trip.destination.latitude},${trip.destination.latitude}" +
                    "&path=color:0x0000ff|weight:5|${trip.origin.latitude},${trip.origin.longitude}|${trip.destination.latitude},${trip.destination.longitude}" +
                    "&key=AIzaSyAcnKK3hN0XKs79ODcaHlm9vP718Ncysfc"

            Glide.with(this).load(mapUrl).into(binding.imageviewMapChooseTrip)
        }
    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}