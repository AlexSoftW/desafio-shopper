package com.application.desafio_shopper.service

import com.application.desafio_shopper.model.Customer
import com.application.desafio_shopper.model.DriverAvailable
import com.application.desafio_shopper.model.RequestRideConfirmBody
import com.application.desafio_shopper.model.RequestRideEstimateBody
import com.application.desafio_shopper.model.Route
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TripService {

    @POST("ride/estimate")
    suspend fun postRideEstimate(@Body data: RequestRideEstimateBody): Route

    @PATCH("ride/confirm")
    suspend fun patchRideConfirm(@Body data: RequestRideConfirmBody): DriverAvailable

    @GET("ride/{customer_id}")
    suspend fun getRideCustomerAndDriver(
        @Path("customer_id") customer_id: String,
        @Query("driver_id") driver_id: String
    ): Customer
}