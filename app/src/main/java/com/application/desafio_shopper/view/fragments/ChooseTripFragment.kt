package com.application.desafio_shopper.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.MyApplication
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

    private lateinit var idCustomer: String
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

        setupView()
        setupObserver()

        return binding.root
    }

    private fun setupView() {
        idCustomer = arguments?.getString("idUser").toString()
        origin = arguments?.getString("origin").toString()
        destination = arguments?.getString("destination").toString()

        binding.textviewValueOriginAddressChooseTrip.text = origin
        binding.textviewValueDestinationAddressChooseTrip.text = destination
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {
        viewModel.postRideEstimate(idCustomer, origin, destination)

        val driveAdapter = DriverAdapter(emptyList()) {
            viewModel.patchRideConfirm(
                RequestRideConfirmBody(
                    idCustomer,
                    origin,
                    destination,
                    distance,
                    duration,
                    Driver(it.id, it.name),
                    it.value
                )
            )
        }

        binding.recyclerviewChooseTrip.adapter = driveAdapter

        viewModel.routeResponse.observe(viewLifecycleOwner) {
            driveAdapter.updateDriverList(it.options)
            distance = it.distance
            duration = it.duration
            binding.textviewQtdDriverAvailableChooseTrip.text = it.options.size.toString()

            val mapUrl = "https://maps.googleapis.com/maps/api/staticmap?size=400x200" +
                    "&markers=color:red|${it.origin.latitude},${it.origin.longitude}" +
                    "&markers=color:blue|${it.destination.latitude},${it.destination.longitude}" +
                    "&path=color:0x0000ff|weight:5|${it.origin.latitude},${it.origin.longitude}|" +
                    "${it.destination.latitude},${it.destination.longitude}" +
                    "&key=${MyApplication().googleApiKey}"

            Glide.with(this).load(mapUrl).into(binding.imageviewMapChooseTrip)
            binding.imageviewMapChooseTrip.visibility = View.VISIBLE
            binding.progressbarChooseTrip.visibility = View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) {
            binding.textviewErrorChooseTrip.visibility = View.VISIBLE
            binding.textviewErrorChooseTrip.text = it.error_description
        }

        viewModel.driverAvailable.observe(viewLifecycleOwner) {
            binding.textviewErrorChooseTrip.visibility = View.GONE
            fragmentReplaceManager(HistoryTripFragment())
        }
    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}