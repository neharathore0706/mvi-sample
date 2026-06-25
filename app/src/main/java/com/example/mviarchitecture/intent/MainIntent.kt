package com.example.mviarchitecture.intent

import com.example.mviarchitecture.data.model.Animal

sealed class MainIntent {
    object fetchAnimal:MainIntent()
    data class SelectAnimal(val animal: Animal) : MainIntent()
    object BackToAnimalList : MainIntent()
}
