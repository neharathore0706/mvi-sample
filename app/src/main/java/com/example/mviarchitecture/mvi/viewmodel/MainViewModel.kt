package com.example.mviarchitecture.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviarchitecture.data.repository.AnimalRepository
import com.example.mviarchitecture.mvi.intent.MainIntent
import com.example.mviarchitecture.mvi.intent.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        onIntent(MainIntent.fetchAnimal)
    }
    fun onIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.fetchAnimal -> fetchAnimals()
            is MainIntent.SelectAnimal -> {
                val currentState = _state.value
                if (currentState is MainState.Animals) {
                    _state.value = MainState.AnimalDetail(
                        animal = intent.animal,
                        animals = currentState.animal
                    )
                }
            }
            MainIntent.BackToAnimalList -> {
                val currentState = _state.value
                if (currentState is MainState.AnimalDetail) {
                    _state.value = MainState.Animals(currentState.animals)
                }
            }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            _state.value = MainState.loading

            try {
                val animals = repository.getAnimals()
                _state.value = MainState.Animals(animals)
            } catch (exception: Exception) {
                _state.value = MainState.Error(
                    exception.message ?: "Unable to fetch animals"
                )
            }
        }
    }
}
