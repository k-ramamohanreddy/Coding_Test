package com.ram.codingtest.repository

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ram.codingtest.api.APIClient
import com.ram.codingtest.api.APIInterface
import com.ram.codingtest.model.NewsResponse
import com.ram.codingtest.ui.main.MainViewModel
import io.mockk.coVerify
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class NewsRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val application = Mockito.mock(Application::class.java)
    private lateinit var newsRepository: NewsRepository
    private lateinit var apiClient: APIClient
    private lateinit var apiInterface: APIInterface

    @Before
    fun setUp() {
        newsRepository = NewsRepository(application)
        apiClient = APIClient
        apiInterface = apiClient.apiService
    }

    @Test
    fun getNews() = runBlocking{
        newsRepository.getNews()
        assertNotNull(newsRepository.newsSuccessLiveData)
    }
}