package com.ram.codingtest.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ram.codingtest.repository.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mainRepository = NewsRepository(application)

    val newsSuccessLiveData = mainRepository.newsSuccessLiveData
    val newsFailureLiveData = mainRepository.newsFailureLiveData

    fun getNews() {
        viewModelScope.launch { mainRepository.getNews() }
    }
}