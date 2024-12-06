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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class RequestTripFragment : Fragment() {
    private lateinit var binding: RequestTripFragmentBinding
    private var currentPage = 0
    private var autoScrollJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RequestTripFragmentBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    private fun startAutoScroll() {
        autoScrollJob = CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            while (isActive) {
                if (binding.viewpagerRequestTrip.adapter != null) {
                    val itemCount = binding.viewpagerRequestTrip.adapter!!.itemCount
                    currentPage = (currentPage + 1) % itemCount
                    binding.viewpagerRequestTrip.setCurrentItem(currentPage, true)
                }
                delay(5000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScrollJob?.cancel()
    }
}