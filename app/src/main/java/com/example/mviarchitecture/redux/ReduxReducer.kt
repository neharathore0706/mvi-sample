package com.example.mviarchitecture.redux

object ReduxReducer {

    fun reduce(
        state: ReduxState,
        action: ReduxAction
    ): ReduxState {
        return when (action) {
            ReduxAction.FetchAnimals -> state.copy(
                isLoading = true,
                error = null
            )

            is ReduxAction.AnimalsLoaded -> state.copy(
                animals = action.animals,
                selectedAnimal = null,
                isLoading = false,
                error = null
            )

            is ReduxAction.FetchFailed -> state.copy(
                isLoading = false,
                error = action.message
            )

            is ReduxAction.SelectAnimal -> state.copy(
                selectedAnimal = action.animal
            )

            ReduxAction.BackToAnimalList -> state.copy(
                selectedAnimal = null
            )
        }
    }
}
