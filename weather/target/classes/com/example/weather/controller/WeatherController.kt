package com.example.weather.controller

import com.example.weather.service.WeatherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/weather")
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/forecast")
    fun getWeatherForecast(): ResponseEntity<Mono<Map<String, Any>>> {
        val weatherData = weatherService.getWeatherForecastForToday()
        return ResponseEntity.ok(weatherData)
    }

}
