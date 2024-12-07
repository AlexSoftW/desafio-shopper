package com.application.desafio_shopper.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.desafio_shopper.model.Option
import com.application.desafio_shopper.model.RouteResponse
import com.application.desafio_shopper.repository.RetrofitRepository
import kotlinx.coroutines.launch

class ChooseTripViewModel : ViewModel() {
    private val repository = RetrofitRepository();

    private val _listTrip = MutableLiveData<List<RouteResponse>>()
    val listTrip: MutableLiveData<List<RouteResponse>> = _listTrip

    private val _listDriver = MutableLiveData<List<Option>>()
    val listDriver: MutableLiveData<List<Option>> = _listDriver

    fun getAllTripViewModel() {
        viewModelScope.launch {
            try {
                val response = repository.getAllTrip()
                _listTrip.value = response
            } catch (e: Exception) {
                Log.e(
                    "ERROR_GET_ALL_TRIP_VIEW_MODEL",
                    "Houve o erro: $e"
                )
            }
        }
    }

    fun getAllDriverViewModel() {
        viewModelScope.launch {
            val listMockDrive = mutableListOf<Option>()

            try {
                val response = repository.getAllTrip()
                for (drive in response) {
                    listMockDrive.addAll(drive.options)
                }
                _listDriver.value = listMockDrive
            } catch (e: Exception) {
                Log.e(
                    "ERROR_GET_ALL_TRIP_VIEW_MODEL",
                    "Houve o erro: $e"
                )
            }
        }
    }
}