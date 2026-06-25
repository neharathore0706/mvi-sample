package com.example.mviarchitecture.intent

import com.example.mviarchitecture.data.model.Animal

sealed class MainState {
    object Idle:MainState()
    object loading:MainState()
    data class Animals(val animal: List<Animal>):MainState()
    data class AnimalDetail(
        val animal: Animal,
        val animals: List<Animal>
    ) : MainState()
    data class Error(val error:String?):MainState()
}
