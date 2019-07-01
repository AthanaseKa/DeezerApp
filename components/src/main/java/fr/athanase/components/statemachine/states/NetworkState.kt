package fr.athanase.components.statemachine.states
sealed class NetworkState {
    object Connected: NetworkState()
    object Disconnected: NetworkState()
}