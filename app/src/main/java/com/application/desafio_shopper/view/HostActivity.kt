package com.application.desafio_shopper.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.desafio_shopper.R
import com.application.desafio_shopper.databinding.HostActivityBinding
import com.application.desafio_shopper.view.fragments.HistoryTripFragment
import com.application.desafio_shopper.view.fragments.RequestTripFragment

class HostActivity : AppCompatActivity() {
    private lateinit var binding: HostActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView();
    }

    private fun setupView() {
        binding = HostActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentStart = RequestTripFragment()
        replaceFragmentManager(fragmentStart)

        binding.imagebuttonNotification.setOnClickListener {
            Toast.makeText(this, "Você não possuí nenhuma notificação", Toast.LENGTH_SHORT).show()
        }

        binding.includeBottomNavigation.linearLayoutHomeNavigation.setOnClickListener {
            replaceFragmentManager(RequestTripFragment())
        }

        binding.includeBottomNavigation.linearLayoutHistoryNavigation.setOnClickListener {
            replaceFragmentManager(HistoryTripFragment())
        }
    }

    private fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

