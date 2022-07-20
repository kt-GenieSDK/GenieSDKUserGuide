package com.kt.gigagenie.geniesdksample.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val logMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            it.value = ""
        }
    }

    internal fun setLogs(log: String) {
        logMessage.value = log + "\n"
    }

    internal fun getLogs(): LiveData<String> {
        return logMessage
    }
}