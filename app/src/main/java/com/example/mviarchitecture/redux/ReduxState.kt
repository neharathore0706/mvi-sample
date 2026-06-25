package com.example.mviarchitecture.redux

import com.example.mviarchitecture.data.model.Animal

data class ReduxState(
    val animals: List<Animal> = emptyList(),
    val selectedAnimal: Animal? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
