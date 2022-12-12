package com.ram.codingtest.repository

import com.ram.codingtest.api.APIClient
import com.ram.codingtest.api.RequestConstants
import com.ram.codingtest.api.RequestErrorCodes.GENERAL_ERROR_CODE
import com.ram.codingtest.model.News
import com.ram.codingtest.utilis.Result
import retrofit2.HttpException

class NewsRepository() : BaseRepository() {
    private val apiService = APIClient.apiService

    suspend fun getNews(apiKey: String = RequestConstants.API_KEY) : Result<ArrayList<News>> {
        var result: Result<ArrayList<News>> = handleSuccess(arrayListOf())
        try {
            val response = apiService.getNews(authorization = apiKey)
            response?.let {
                it.body()?.news?.let { photosResponse ->
                    result = handleSuccess(photosResponse)
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: HttpException) {
            return handleException(error.code())
        }
        return result
    }
}