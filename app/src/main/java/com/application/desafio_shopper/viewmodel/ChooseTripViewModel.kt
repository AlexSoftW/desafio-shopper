package com.application.desafio_shopper.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.desafio_shopper.model.ErrorResponse
import com.application.desafio_shopper.model.RequestRideConfirmBody
import com.application.desafio_shopper.model.RequestRideEstimateBody
import com.application.desafio_shopper.model.Route
import com.application.desafio_shopper.repository.RetrofitRepository
import kotlinx.coroutines.launch

class ChooseTripViewModel : ViewModel() {
    private val repository = RetrofitRepository()

    private val _routeResponse = MutableLiveData<Route>()
    val routeResponse: MutableLiveData<Route> = _routeResponse

    private val _error = MutableLiveData<ErrorResponse>()
    val error: MutableLiveData<ErrorResponse> = _error

    fun postRideEstimate(idUserValue: String, originValue: String, destinationValue: String) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceTrip.POST_RIDE_ESTIMATE(
                    RequestRideEstimateBody(
                        customer_id = idUserValue,
                        origin = originValue,
                        destination = destinationValue
                    )
                )
                _routeResponse.value = response
                Log.i("RESPONSE_TESTE", "dados de resposta: $response")

            } catch (e: Exception) {
                Log.e("ERROR_POST_RIDE_ESTIMATE", "error: $e")
            }
        }
    }

    fun patchRideConfirm(body: RequestRideConfirmBody) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceTrip.PATCH_RIDE_CONFIRM(body)
                Log.i("TAG_PATCH_TESTE", "patchRideConfirm resposta: $response")
            } catch (e: Exception) {
                Log.e("TAG_PATCH_TESTE", "patchRideConfirm ERRO: $e", )
            }
        }
    }
}