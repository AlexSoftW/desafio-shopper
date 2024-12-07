package com.application.desafio_shopper.repository

import com.application.desafio_shopper.model.Location
import com.application.desafio_shopper.model.Option
import com.application.desafio_shopper.model.Review
import com.application.desafio_shopper.model.RouteResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val listResponseMock = listOf(
        RouteResponse(
            origin = Location(latitude = -23.5505, longitude = -46.6333),
            destination = Location(latitude = -22.9068, longitude = -43.1729),
            distance = 400.5,
            duration = "5 hours 30 minutes",
            options = listOf(
                Option(
                    id = 1,
                    name = "Mark Johnson",
                    description = "Affordable option for long trips",
                    vehicle = "Compact - Honda Civic",
                    review = Review(rating = 3, comment = "Decent, but could be more comfortable"),
                    value = 150.0
                ),
                Option(
                    id = 3,
                    name = "Emily Davis",
                    description = "High-end vehicle with premium features",
                    vehicle = "Luxury Sedan - Mercedes-Benz S-Class",
                    review = Review(rating = 5, comment = "Perfect experience"),
                    value = 500.0
                )
            ),
            routeResponse = Any()
        ),
        RouteResponse(
            origin = Location(latitude = 40.7128, longitude = -74.0060),
            destination = Location(latitude = 34.0522, longitude = -118.2437),
            distance = 4500.0,
            duration = "1 day 3 hours",
            options = listOf(
                Option(
                    id = 1,
                    name = "Standard",
                    description = "Affordable option for long trips",
                    vehicle = "Compact",
                    review = Review(rating = 3, comment = "Decent, but could be more comfortable"),
                    value = 150.0
                ),
                Option(
                    id = 3,
                    name = "Premium",
                    description = "High-end vehicle with premium features",
                    vehicle = "Luxury Sedan",
                    review = Review(rating = 5, comment = "Perfect experience"),
                    value = 500.0
                )
            ),
            routeResponse = Any()
        ),
        RouteResponse(
            origin = Location(latitude = 51.5074, longitude = -0.1278),
            destination = Location(latitude = 48.8566, longitude = 2.3522),
            distance = 1000.0,
            duration = "12 hours",
            options = listOf(
                Option(
                    id = 1,
                    name = "Alex Turner",
                    description = "Economical option for budget travel",
                    vehicle = "Hatchback - Ford Fiesta",
                    review = Review(rating = 2, comment = "Not the most comfortable ride"),
                    value = 80.0
                )
            ),
            routeResponse = Any()
        ),
        RouteResponse(
            origin = Location(latitude = 37.7749, longitude = -122.4194),
            destination = Location(latitude = 34.0522, longitude = -118.2437),
            distance = 560.0,
            duration = "7 hours",
            options = listOf(
                Option(
                    id = 2,
                    name = "Sophia Lee",
                    description = "Comfortable vehicle for long trips",
                    vehicle = "SUV - Audi Q7",
                    review = Review(rating = 4, comment = "Good option for a smooth ride"),
                    value = 200.0
                ),
                Option(
                    id = 3,
                    name = "Oliver Brown",
                    description = "Top of the line vehicle with all the luxury",
                    vehicle = "Convertible - Porsche 911",
                    review = Review(rating = 5, comment = "Fantastic ride with great features"),
                    value = 600.0
                )
            ),
            routeResponse = Any()
        ),
        RouteResponse(
            origin = Location(latitude = -34.6037, longitude = -58.3816),
            destination = Location(latitude = -32.0464, longitude = -61.2114),
            distance = 120.0,
            duration = "2 hours",
            options = listOf(
                Option(
                    id = 1,
                    name = "Lucas Gonzalez",
                    description = "Simple and affordable",
                    vehicle = "Hatchback - Hyundai Accent",
                    review = Review(rating = 4, comment = "Good value for the price"),
                    value = 50.0
                )
            ),
            routeResponse = Any()
        )
    )

    fun getAllTrip(): List<RouteResponse> {
        return listResponseMock
    }
}