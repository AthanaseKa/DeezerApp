package fr.athanase.backend.dao

import androidx.lifecycle.MutableLiveData
import io.realm.*
import timber.log.Timber

suspend fun RealmConfiguration.flushDatabase(): Boolean {
    return startTransaction {
        it.deleteAll()
        Timber.d("Database has been flushed")
        return@startTransaction true
    }
}

suspend inline fun <reified Class : RealmObject> RealmConfiguration.flushEntityFromDatabase(crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) }): Boolean {
    return startTransaction {
        it.query().findAll()?.deleteAllFromRealm()
        return@startTransaction true
    }
}

suspend inline fun <reified Class : RealmObject> RealmConfiguration.fetchSingleItem(crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) }): Class? {
    return fetchItems(query)
        .firstOrNull()
}

suspend inline fun <reified Class : RealmObject> RealmConfiguration.saveSingleItemTransaction(data: Class) {
    saveMultipleItemTransaction(listOf(data))
}

suspend inline fun <reified Class : RealmObject> RealmConfiguration.fetchItems(crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) }): List<Class> {
    return openInstanceForFetch {
        val data: List<Class> = it.query().findAll()

        return@openInstanceForFetch it.copyFromRealm(data)
    }
}

suspend inline fun <reified Class : RealmObject> RealmConfiguration.updateItemTransaction(crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) }, crossinline update: (data: Class?) -> Class?): Class? {
    return startTransaction { realm ->
        val data: Class? = realm.query().findFirst()

        return@startTransaction data?.let {
            val result = update(it)
            return@let realm.copyFromRealm(result ?: throw IllegalArgumentException())
        }
    }
}

suspend inline fun <reified Class : List<RealmObject>> RealmConfiguration.saveMultipleItemTransaction(data: Class): Class {
    return startTransaction {
        it.copyToRealmOrUpdate(data)
        return@startTransaction data
    }
}

suspend inline fun <ReturnType> RealmConfiguration.startTransaction(crossinline func: (realm: Realm) -> ReturnType): ReturnType {
    return openInstanceForTransaction {
        var result: ReturnType? = null
        it.executeTransaction { realm ->
            result = func(realm)
        }

        return@openInstanceForTransaction result ?: throw NullPointerException()
    }
}

suspend inline fun <reified ReturnType> RealmConfiguration.openInstanceForFetch(crossinline func: (realm: Realm) -> ReturnType?): ReturnType {
    val realm = Realm.getInstance(this)
    val results = func(realm)
    realm.close()

    return results?.let { result ->
        result
    } ?: throw Throwable(ReturnType::class.toString())
}

suspend inline fun <ReturnType> RealmConfiguration.openInstanceForTransaction(crossinline func: (realm: Realm) -> ReturnType): ReturnType {
    val realm = Realm.getInstance(this)
    val results = func(realm)
    realm.close()
    return results
}

internal inline fun <reified Class: RealmObject, Entity> RealmConfiguration.observeDataList(
    crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) },
    noinline transform: (List<Class>) -> List<Entity>
): MutableLiveData<List<Entity>> {
    return Realm.getInstance(this)
        .query()
        .findAllAsync()
        .asListLiveData(Realm.getInstance(this), transform)
}

internal inline fun <reified Class : RealmObject, Entity> RealmConfiguration.observeData(
    crossinline query: Realm.() -> RealmQuery<Class> = { where(Class::class.java) },
    noinline transform: (Class) -> Entity
): MutableLiveData<Entity?> {
    return Realm.getInstance(this)
        .query()
        .findAllAsync()
        .asLiveData(Realm.getInstance(this), transform)
}

internal fun <T: RealmModel, Entity> RealmResults<T>.asListLiveData(
    realm: Realm? = null,
    transform: (List<T>) -> List<Entity>
): MutableLiveData<List<Entity>> = RealmListLiveData(
    this,
    realm,
    transform
)

internal fun <T: RealmModel, Entity> RealmResults<T>.asLiveData(
    realm: Realm? = null,
    transform: (T) -> Entity
): MutableLiveData<Entity?> = RealmLiveData(
    this,
    realm,
    transform
)

internal class RealmListLiveData<T : RealmModel, Entity>(
    private val results: RealmResults<T>,
    val realm: Realm? = null,
    private val transform: (List<T>) -> List<Entity>
): MutableLiveData<List<Entity>>() {
    private val listener = RealmChangeListener<RealmResults<T>> {
            results -> value = transform(realm?.copyFromRealm(results) ?: results)
    }
    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}

internal class RealmLiveData<T : RealmModel, Entity>(
    private val results: RealmResults<T>,
    val realm: Realm? = null,
    private val transform: (T) -> Entity
): MutableLiveData<Entity?>() {
    private val listener = RealmChangeListener<RealmResults<T>> {
            results -> value = results.firstOrNull()?.let { transform(realm?.copyFromRealm(it) ?: it) }
    }
    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}