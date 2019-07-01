package fr.athanase.backend.error

import fr.athanase.entites.StateErrorHandler
import fr.athanase.entites.StateErrorType

class ErrorHandler : StateErrorHandler {

    override fun getErrorType(error: Throwable): StateErrorType {
        return when {
            error.isNetworkError() -> StateErrorType.NETWORK_ERROR
            error.isEndPointError() -> StateErrorType.ENDPOINT_ERROR
            else -> StateErrorType.ERROR
        }
    }
}