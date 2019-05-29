package fr.athanase.deezerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import fr.athanase.backend.dao.DatabaseInstance
import fr.athanase.deezerapp.databinding.ActivityDeezerBinding
import timber.log.Timber

class DeezerActivity : AppCompatActivity() {
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.deezerNavFragment)
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        DatabaseInstance.init(this)
        val binding: ActivityDeezerBinding = DataBindingUtil.setContentView(this, R.layout.activity_deezer)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
//        setupConnectivityManager()
    }


//    private fun setupConnectivityManager() {
//
//        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val builder =  NetworkRequest.Builder()
//
//         val networkCallback = object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network?) {
//                sendBroadcast(getConnectivityIntent(true))
//            }
//
//            override fun onLost(network: Network?) {
//                sendBroadcast(getConnectivityIntent(false))
//            }
//        }
//
//        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
//    }
//
//    private fun  getConnectivityIntent(noConnection: Boolean) : Intent
//    {
////        Timber.e("CONNECTIVITY")
//        val intent = Intent()
//
//        intent.action = "fr.athanase.deezerapp.CONNECTIVITY_CHANGE"
//        intent.putExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, noConnection)
//
//        return intent
//
//    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}