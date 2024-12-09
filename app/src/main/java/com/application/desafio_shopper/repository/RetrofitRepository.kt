package com.application.desafio_shopper.repository

import com.application.desafio_shopper.service.TripService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServiceTrip: TripService =
        retrofit.create(TripService::class.java)
}