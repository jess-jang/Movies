package com.jess.movies.di.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author jess
 * @since 2020.06.12
 */
interface DispatcherProvider {
    val job: Job
    fun main(): CoroutineContext
    fun io(): CoroutineContext
    fun default(): CoroutineContext
}

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val job: Job = Job()
    override fun main(): CoroutineContext = Dispatchers.Main + job
    override fun io(): CoroutineContext = Dispatchers.IO + job
    override fun default(): CoroutineContext = Dispatchers.Default + job

}