package fr.athanase.components.test.states

sealed class State {
    object Loading : State()
    object Success : State()
    object Update : State()

//    class Success<out S>(val state: S) : State()
//    class Update<out S>(val state: S) : State()
    class Error(val error: Throwable) : State()
}