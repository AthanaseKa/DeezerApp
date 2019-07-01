package fr.athanase.components.statemachine.states

sealed class State {
    object Loading : State()
    object Success : State()
    object Update : State()
    object Empty : State()
    object Error : State()
    object NetworkError : State()
}