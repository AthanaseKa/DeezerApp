package fr.athanase.backend.dao

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class DatabaseInstance {
    companion object {
        internal lateinit var deezerRealm: RealmConfiguration

        fun init(applicationContext: Context) {
            Realm.init(applicationContext)
            deezerRealm = RealmConfiguration.Builder()
                .name("deezer.realm")
                .deleteRealmIfMigrationNeeded()
                .modules(DeezerRealmModule())
                .build()
        }

    }
}