package fr.athanase.entites

interface StateErrorHandler {
    fun getErrorType(error: Throwable) : StateErrorType
}