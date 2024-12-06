package com.application.desafio_shopper.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.databinding.ChooseTripFragmentBinding

class ChooseTripFragment : Fragment() {
    private lateinit var binding: ChooseTripFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChooseTripFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}