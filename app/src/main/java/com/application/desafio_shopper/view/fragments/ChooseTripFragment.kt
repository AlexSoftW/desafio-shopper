package com.application.desafio_shopper.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.R
import com.application.desafio_shopper.adapter.DriverAdapter
import com.application.desafio_shopper.databinding.ChooseTripFragmentBinding
import com.application.desafio_shopper.model.Driver
import com.application.desafio_shopper.model.RequestRideConfirmBody
import com.application.desafio_shopper.viewmodel.ChooseTripViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseTripFragment : Fragment() {
    private lateinit var binding: ChooseTripFragmentBinding
    private val viewModel: ChooseTripViewModel by viewModel()

    private lateinit var idUser: String
    private lateinit var origin: String
    private lateinit var destination: String
    private var distance: Long = 0
    private lateinit var duration: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChooseTripFragmentBinding.inflate(inflater, container, false)

        idUser = arguments?.getString("idUser").toString()
        origin = arguments?.getString("origin").toString()
        destination = arguments?.getString("destination").toString()

        Log.d("ChooseTripFragment", "Received origin: $origin, destination: $destination")

        binding.textviewValueStartAddressChooseTrip.text = origin
        binding.textviewValueFinalAddressChooseTrip.text = destination

        setupObserver()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {
        val driveAdapter = DriverAdapter(emptyList()) {
            viewModel.patchRideConfirm(
                RequestRideConfirmBody(
                    idUser,
                    origin,
                    destination,
                    distance,
                    duration,
                    Driver(it.id, it.name),
                    it.value
                )
            )
            fragmentReplaceManager(HistoryTripFragment())
        }

        viewModel.postRideEstimate(idUser, origin, destination)

        viewModel.routeResponse.observe(viewLifecycleOwner) {
            distance = it.distance
            duration = it.duration
            binding.textviewQtdDriverAvaibleChooseTrip.text = it.options.size.toString()
        }

        viewModel.routeResponse.observe(viewLifecycleOwner) {
            Log.d("ChooseTripFragment", "RouteResponse received: $it")
            val mapUrl = "https://maps.googleapis.com/maps/api/staticmap?size=400x200" +
                    "&markers=color:red|${it.origin.latitude},${it.origin.longitude}" +
                    "&markers=color:blue|${it.destination.latitude},${it.destination.longitude}" +
                    "&path=color:0x0000ff|weight:5|${it.origin.latitude},${it.origin.longitude}|" +
                    "${it.destination.latitude},${it.destination.longitude}" +
                    "&key=AIzaSyCkLFTR2W7ubSgHnicZk7Ij8hInZzfhXCE"

            Glide.with(this).load(mapUrl).into(binding.imageviewMapChooseTrip)
        }

        viewModel.routeResponse.observe(viewLifecycleOwner, { driveList ->
            driveAdapter.updateDriverList(driveList.options)
        })

        binding.recyclerviewChooseTrip.adapter = driveAdapter
    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}