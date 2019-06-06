package fr.athanase.components.test.statefragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

sealed class NetworkState {
    object Connected: NetworkState()
    object Disconnected: NetworkState()
}

class NetworkLiveData(application: Application) : LiveData<NetworkState>() {

    private var  connectivityManager: ConnectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network?) {
            postValue(NetworkState.Connected)
        }

        override fun onLost(network: Network?) {
            postValue(NetworkState.Disconnected)
        }
    }

    override fun onActive() {
        super.onActive()

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        if (activeNetwork?.isConnectedOrConnecting == true) {
            value = NetworkState.Connected
        } else {
            value = NetworkState.Disconnected
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}