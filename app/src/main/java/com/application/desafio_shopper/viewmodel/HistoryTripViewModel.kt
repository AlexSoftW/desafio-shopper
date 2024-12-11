package com.application.desafio_shopper.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.desafio_shopper.model.Customer
import com.application.desafio_shopper.model.ErrorResponse
import com.application.desafio_shopper.repository.RetrofitRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HistoryTripViewModel : ViewModel() {
    private val repository = RetrofitRepository()
    private val gson = Gson()

    private val _responseCustomerHistory = MutableLiveData<Customer>()
    val responseCustomerHistory: MutableLiveData<Customer> = _responseCustomerHistory

    private val _error = MutableLiveData<ErrorResponse>()
    val error: MutableLiveData<ErrorResponse> = _error

    fun getCustomerHistory(idCustomer: String, idDriver: String) {
        viewModelScope.launch {
            try {
                val customerId = if (idCustomer.isBlank()) "0" else idCustomer
                val response = repository.apiServiceTrip.getRideCustomerAndDriver(
                    customerId,
                    idDriver
                )
                _responseCustomerHistory.value = response
                Log.i("TAG_HISTORY_TRIP_VIEWMODEL", "response: $response")

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("TAG_HISTORY_TRIP_VIEWMODEL", "error: $errorBody")

                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _error.value = errorResponse
                Log.e(
                    "TAG_HISTORY_TRIP_VIEWMODEL",
                    "error: $e"
                )
            }
        }
    }
}