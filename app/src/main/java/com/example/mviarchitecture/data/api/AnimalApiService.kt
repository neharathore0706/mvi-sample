package com.example.mviarchitecture.data.api

import com.example.mviarchitecture.data.model.Animal
import retrofit2.http.GET

interface AnimalApiService {

    @GET("Aliendroid8045/b09f9ac24273b6fd8e5184bdf1d3a62e/raw/c0fbbe02a3973477f3e18fdf16cb9b1a7f979f6a/Animal.Json")
    suspend fun getAnimals(): List<Animal>
}
