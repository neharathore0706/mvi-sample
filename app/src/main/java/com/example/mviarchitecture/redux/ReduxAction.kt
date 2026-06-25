package com.example.mviarchitecture.redux

import com.example.mviarchitecture.data.model.Animal

sealed interface ReduxAction {
    data object FetchAnimals : ReduxAction
    data class AnimalsLoaded(val animals: List<Animal>) : ReduxAction
    data class FetchFailed(val message: String) : ReduxAction
    data class SelectAnimal(val animal: Animal) : ReduxAction
    data object BackToAnimalList : ReduxAction
}
