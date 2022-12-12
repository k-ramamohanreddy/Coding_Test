package com.ram.codingtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.codingtest.model.News
import com.ram.codingtest.repository.NewsRepository
import kotlinx.coroutines.launch
import com.ram.codingtest.utilis.Result

class MainViewModel() : ViewModel() {
    private val newsRepository = NewsRepository()

    val newsLiveData = MutableLiveData<Result<List<News>>>()

    fun getNews() {
        try {
            viewModelScope.launch {
                val response = newsRepository.getNews()
                response.let {
                when (it) {
                    is Result.Success -> {
                        val newsList = it.extractData
                        newsList?.let { list ->
                            newsLiveData.postValue(Result.Success(list))
                        }
                    }
                    else -> newsLiveData.postValue(it)
                }
            }
            }
        } catch (exception : Exception) {
            newsLiveData.postValue(Result.Error(exception))
        }
    }

    fun getNewsLiveData(): LiveData<Result<List<News>>> {
        return newsLiveData
    }
}