package com.jess.movies.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jess.movies.di.provider.DispatcherProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

interface BaseDataSource {

    val isRequest: LiveData<Boolean>
    val dispatcher: DispatcherProvider

    fun onCleared() {
        dispatcher.job.cancel()
    }
}

abstract class BaseDataSourceImpl(

) : BaseDataSource {

    val _isRequest = MutableLiveData<Boolean>()
    override val isRequest: LiveData<Boolean> get() = _isRequest

}
