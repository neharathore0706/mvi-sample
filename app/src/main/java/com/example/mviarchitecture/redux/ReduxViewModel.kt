package com.example.mviarchitecture.redux

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviarchitecture.data.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ReduxViewModel @Inject constructor(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ReduxState())
    val state: StateFlow<ReduxState> = _state.asStateFlow()

    init {
        dispatch(ReduxAction.FetchAnimals)
    }

    fun dispatch(action: ReduxAction) {
        if (action is ReduxAction.FetchAnimals && _state.value.isLoading) return

        _state.value = ReduxReducer.reduce(_state.value, action)

        if (action is ReduxAction.FetchAnimals) {
            fetchAnimals()
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            try {
                dispatch(
                    ReduxAction.AnimalsLoaded(repository.getAnimals())
                )
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (exception: Exception) {
                dispatch(
                    ReduxAction.FetchFailed(
                        exception.message ?: "Unable to fetch animals"
                    )
                )
            }
        }
    }
}
