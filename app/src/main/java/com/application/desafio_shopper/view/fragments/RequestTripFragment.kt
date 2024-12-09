package com.application.desafio_shopper.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.R
import com.application.desafio_shopper.adapter.CarouselAdapter
import com.application.desafio_shopper.databinding.RequestTripFragmentBinding
import com.application.desafio_shopper.model.CarouselItem
import com.application.desafio_shopper.viewmodel.ChooseTripViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestTripFragment : Fragment() {
    private lateinit var binding: RequestTripFragmentBinding
    private val viewModel: ChooseTripViewModel by viewModel()

    private var currentSlide = 0
    private var autoScrollJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RequestTripFragmentBinding.inflate(inflater, container, false)
        setupView()
        setupObserver()

        return binding.root
    }

    private fun setupView() {
        val items = listOf(
            CarouselItem(
                R.drawable.background1,
                "1.Viaje tranquilamente com a Shopper Drive, economize \$ e tenha conforto e segurança em suas viagens"
            ),
            CarouselItem(
                R.drawable.background2,
                "2.Viaje tranquilamente com a Shopper Drive, economize \$ e tenha conforto e segurança em suas viagens"
            ),
            CarouselItem(
                R.drawable.background3,
                "3.Viaje tranquilamente com a Shopper Drive, economize \$ e tenha conforto e segurança em suas viagens"
            ),
        )

        val adapter = CarouselAdapter(items)
        binding.viewpagerRequestTrip.adapter = adapter
        startAutoScroll()
    }

    private fun setupObserver() {
        binding.buttonRequestTrip.setOnClickListener {
            val fragment = ChooseTripFragment()

            val idUser = binding.edittextIdUserRequestTrip.text.toString()
            val startAddress = binding.edittextStartAddressRequestTrip.text.toString()
            val finalAddress = binding.edittextFinalAddressRequestTrip.text.toString()

            val bundle = Bundle().apply {
                putString("idUser", idUser)
                putString("origin", startAddress)
                putString("destination", finalAddress)
            }

            fragment.arguments = bundle
            fragmentReplaceManager(fragment)
        }
    }

    private fun startAutoScroll() {
        autoScrollJob = CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            while (isActive) {
                if (binding.viewpagerRequestTrip.adapter != null) {
                    val itemCount = binding.viewpagerRequestTrip.adapter!!.itemCount
                    currentSlide = (currentSlide + 1) % itemCount
                    binding.viewpagerRequestTrip.setCurrentItem(currentSlide, true)
                }
                delay(5000)
            }
        }
    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}