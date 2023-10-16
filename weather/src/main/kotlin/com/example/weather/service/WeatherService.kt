package com.example.weather.service

import com.example.weather.framework.beans.WeatherResponse
import com.example.weather.framework.constants.Constants
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WeatherService {
    fun getWeatherForecastForToday(): Mono<Map<String, Any>> {
        try {
            val webClient = WebClient.create(Constants.API_URL)

            return webClient.get()
                    .retrieve()
                    .bodyToMono(WeatherResponse::class.java)
                    .map { response -> transformResponse(response) }
        } catch (e: Exception) {
            return Mono.error(e)
        }
    }

    private fun transformResponse(response: WeatherResponse): Map<String, Any> {
        val currentDay = response.properties.periods[0]

        val result = mapOf(
                "daily" to mapOf(
                        "day name" to currentDay.name,
                        "temp_high_celsius" to currentDay.temperature,
                        "forecast blurp" to currentDay.shortForecast
                )
        )

        return result
    }

}
