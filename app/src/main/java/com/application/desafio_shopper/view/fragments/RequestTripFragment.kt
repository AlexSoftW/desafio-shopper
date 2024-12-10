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
    private var currentSlide = 0
    private var autoScrollJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RequestTripFragmentBinding.inflate(inflater, container, false)
        setupView()

        return binding.root
    }

    private fun setupView() {
        val items = listOf(
            CarouselItem(
                R.drawable.background1,
                "Conectando você ao melhor",
                "Oferecemos praticidade e segurança para motoristas e passageiros, criando a experiência ideal em cada viagem."
            ),
            CarouselItem(
                R.drawable.background2,
                "Viagem segura e confiável",
                "Viaje com tranquilidade, preços acessíveis e uma experiência confortável em cada trajeto."
            ),
            CarouselItem(
                R.drawable.background3,
                "Ganhe mais, dirija melhor",
                "Com taxas competitivas, suporte 24 horas e liberdade para definir sua rotina, dirigir com a Shopper Driver nunca foi tão vantajoso!"
            ),
        )
        val adapter = CarouselAdapter(items)

        binding.viewpagerRequestTrip.adapter = adapter
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

        startAutoScroll()
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