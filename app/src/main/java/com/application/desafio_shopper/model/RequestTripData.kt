package com.application.desafio_shopper.model

data class Route(
    val origin: Location,
    val destination: Location,
    val distance: Long,
    val duration: String,
    val options: List<Option>,
    val routeResponse: Any
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class Option(
    val id: Int,
    val name: String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)

data class Review(
    val rating: Int,
    val comment: String
)

data class DriverAvailable(
    val success: Boolean
)

data class ErrorResponse(
    val error_code: String,
    val error_description: String
)

//data class request body.
data class RequestRideEstimateBody(
    val customer_id: String?,
    val origin: String?,
    val destination: String?
)

data class RequestRideConfirmBody(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Long,
    val duration: String,
    val driver: Driver,
    val value: Double
)