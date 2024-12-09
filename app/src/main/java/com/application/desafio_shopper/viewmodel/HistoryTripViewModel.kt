package com.application.desafio_shopper.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.desafio_shopper.model.Customer
import com.application.desafio_shopper.repository.RetrofitRepository
import kotlinx.coroutines.launch

class HistoryTripViewModel : ViewModel() {
    private val repository = RetrofitRepository()

    private val _responseCustomerHistory = MutableLiveData<Customer>()
    val responseCustomerHistory: MutableLiveData<Customer> = _responseCustomerHistory

    fun getCustomerHistory(idUser: String, idDriver: String) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceTrip.GET_RIDE_CUSTOMER_AND_DRIVER(
                    idUser,
                    idDriver
                )
                _responseCustomerHistory.value = response
            } catch (e: Exception) {
                Log.e("TAG_ERROR_CUSTOMER", "ERROR_GET_CUSTOMER_HISTORY: error $e")
            }
        }
    }

}