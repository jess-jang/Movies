package com.jess.movies.domain.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jess.movies.common.base.BaseDataSource
import com.jess.movies.common.base.BaseDataSourceImpl
import com.jess.movies.common.extension.safeScope
import com.jess.movies.di.provider.DispatcherProvider
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
interface LiveDataSource : BaseDataSource {
    val result: LiveData<String>
    suspend fun start()
}

class LiveDataSourceImpl @Inject constructor(
    override val dispatcher: DispatcherProvider
) : BaseDataSourceImpl(), LiveDataSource {

    private val query = MutableLiveData<String>()
    override val result: LiveData<String> get() = query

    override suspend fun start() {
        val jon = CoroutineScope(dispatcher.default()).safeScope().launch {
            var i = 0
            while (i < 1000 && isActive) {
                delay(500)
                val value = "$isActive value : ${i++}"
                query.postValue(value)
                Timber.d(value)
            }
        }
        Timber.d(jon.toString())

        val jon2 = CoroutineScope(dispatcher.default()).launch {
            var i = 0
            while (i < 1000 && isActive) {
                delay(500)
                val value = "$isActive value : ${i++}"
                query.postValue(value)
                Timber.d(value)
            }
        }
        Timber.d(jon2.toString())
    }
}
