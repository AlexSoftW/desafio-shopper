package com.application.desafio_shopper.model

data class RouteResponse(
    val origin: Location,
    val destination: Location,
    val distance: Double,
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

data class ErrorResponse(
    val error_code: String,
    val error_description: String
)