package com.ram.codingtest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ram.codingtest.api.APIClient
import com.ram.codingtest.api.RequestErrorCodes.SOMETHING_WRONG
import com.ram.codingtest.utilis.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var newsRepository: NewsRepository
    private lateinit var apiClient: APIClient

    @Before
    fun setUp() {
        newsRepository = NewsRepository()
        apiClient = APIClient
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            val apiResponse = newsRepository.getNews()
            assertNotNull(apiResponse)
        }
    }

    @Test
    fun `given response failure when fetching results then return exception`() {
        runBlocking {
            val apiResponse = newsRepository.getNews("un_known_error_api_key")
            assertNotNull(apiResponse)
            val expectedValue = Result.Error(Exception(SOMETHING_WRONG))
            assertEquals(expectedValue.exception.message, (apiResponse as Result.Error).exception.message)
        }
    }
}