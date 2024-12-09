package com.application.desafio_shopper.model

data class Customer(
    val customerId: String,
    val rides: List<Ride>
)

data class Ride(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class Driver(
    val id: Int,
    val name: String
)