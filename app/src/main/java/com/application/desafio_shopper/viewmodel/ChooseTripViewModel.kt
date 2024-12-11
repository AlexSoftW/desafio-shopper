package com.application.desafio_shopper.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.desafio_shopper.model.DriverAvailable
import com.application.desafio_shopper.model.ErrorResponse
import com.application.desafio_shopper.model.RequestRideConfirmBody
import com.application.desafio_shopper.model.RequestRideEstimateBody
import com.application.desafio_shopper.model.Route
import com.application.desafio_shopper.repository.RetrofitRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ChooseTripViewModel : ViewModel() {
    private val repository = RetrofitRepository()
    private val gson = Gson()

    private val _routeResponse = MutableLiveData<Route>()
    val routeResponse: MutableLiveData<Route> = _routeResponse

    private val _driverAvailable = MutableLiveData<DriverAvailable>()
    val driverAvailable: MutableLiveData<DriverAvailable> = _driverAvailable

    private val _error = MutableLiveData<ErrorResponse>()
    val error: MutableLiveData<ErrorResponse> = _error

    fun postRideEstimate(idUserValue: String, originValue: String, destinationValue: String) {
        viewModelScope.launch {
            try {
                //OBS: Aqui eu inseri os valores vazios para receberem 'null',
                // na API quando informa " ", ele retorna 200 ao invés de 400.
                val idUserConverted: String? = if (idUserValue.isEmpty()) null else idUserValue
                val originConverted: String? = if (originValue.isEmpty()) null else originValue
                val destinationConverted: String? =
                    if (destinationValue.isEmpty()) null else destinationValue

                val response = repository.apiServiceTrip.postRideEstimate(
                    RequestRideEstimateBody(
                        customer_id = idUserConverted,
                        origin = originConverted,
                        destination = destinationConverted
                    )
                )
                _routeResponse.value = response
                Log.i("RESPONSE_TESTE", "dados de resposta: $response")

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _error.value = errorResponse
                Log.e("ERROR_POST_RIDE_ESTIMATE", "error: ${errorResponse.error_description}")
            }
        }
    }

    fun patchRideConfirm(body: RequestRideConfirmBody) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceTrip.patchRideConfirm(body)
                _driverAvailable.value = response
                Log.i("TAG_PATCH_TESTE", "patchRideConfirm resposta: $response")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _error.value = errorResponse
                Log.e("TAG_PATCH_TESTE", "patchRideConfirm ERRO: $e")
            }
        }
    }
}