package com.ram.codingtest.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ram.codingtest.api.APIClient
import com.ram.codingtest.model.NewsResponse
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NewsRepository(private val application: Application) {
    private val apiService = APIClient.apiService
    var newsSuccessLiveData = MutableLiveData<NewsResponse>()
    var newsFailureLiveData = MutableLiveData<Boolean>()

    suspend fun getNews() {
        try {
            val response = apiService.getNews()
            if (response.isSuccessful) {
                newsSuccessLiveData.postValue(response.body())
            } else {
                newsFailureLiveData.postValue(true)
            }
        } catch (e: UnknownHostException) {
            newsFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            newsFailureLiveData.postValue(true)
        } catch (e: Exception) {
            newsFailureLiveData.postValue(true)
        }
    }
}