package fr.athanase.backend.error

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

fun Throwable.isNetworkError(): Boolean {
    return (this is UnknownHostException
            || this is SocketTimeoutException
            || this is SocketTimeoutException
            || this is UnknownServiceException)
}

fun Throwable.isEndPointError() : Boolean {
    return (this is HttpException && this.code() in 399..600)
}

