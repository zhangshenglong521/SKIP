package com.android.skip.data.config

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val configReadRepository: ConfigReadRepository,
    private val configLoadRepository: ConfigLoadRepository
) : ViewModel() {
    var configHashCode: LiveData<Int> = configReadRepository.configHashCode

    init {
        configReadRepository.configHashCode.observeForever{
            val configMap = configReadRepository.handleConfig()
            configLoadRepository.loadConfig(configMap)
        }
    }

    fun readConfig(context: Context) = configReadRepository.readConfig(context)
}