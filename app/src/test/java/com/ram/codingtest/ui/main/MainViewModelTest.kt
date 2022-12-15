package com.ram.codingtest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ram.codingtest.api.APIInterface
import com.ram.codingtest.model.News
import com.ram.codingtest.model.NewsResponse
import com.ram.codingtest.repository.NewsRepository
import com.ram.codingtest.utilis.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: MainViewModel

    @Mock
    val apiInterface: APIInterface = Mockito.mock(APIInterface::class.java)


    @Mock
    private lateinit var apiNewsObserver: Observer<Result<List<News>>>

    @Before
    fun setUp() {
        viewModel = MainViewModel()
        newsRepository = NewsRepository()
    }

    @Test
    fun givenServerResponse200_shouldReturnSuccess() {
        val res = NewsResponse(arrayListOf(), 1, "success")
        testCoroutineRule.runBlockingTest {
            `when`(apiInterface.getNews()).thenReturn(Response.success(res))
            viewModel.getNews()
            viewModel.getNewsLiveData().observeForever(apiNewsObserver)
            Assert.assertNotNull(viewModel.getNewsLiveData())

        }
    }

    @Test
    fun givenServerResponseError_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            `when`(apiInterface.getNews()).thenThrow(java.lang.RuntimeException(errorMessage))
            viewModel.getNews()
            viewModel.getNewsLiveData().observeForever(apiNewsObserver)
        }
    }

    @After
    fun tearDown() {
        viewModel.getNewsLiveData().removeObserver(apiNewsObserver)
    }


}
