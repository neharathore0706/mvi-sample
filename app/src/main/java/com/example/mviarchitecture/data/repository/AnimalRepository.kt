package com.example.mviarchitecture.data.repository

import com.example.mviarchitecture.data.api.AnimalApiService
import com.example.mviarchitecture.data.model.Animal
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    private val animalApiService: AnimalApiService
) {

    suspend fun getAnimals(): List<Animal> {
        return animalApiService.getAnimals()
    }
}
