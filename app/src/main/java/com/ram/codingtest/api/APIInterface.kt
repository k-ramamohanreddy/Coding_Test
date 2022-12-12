package com.ram.codingtest.api

import com.ram.codingtest.api.RequestConstants.API_KEY
import com.ram.codingtest.api.RequestConstants.LANGUAGE_CODE
import com.ram.codingtest.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIInterface {

//    @GET("api/1/news?")
//    suspend fun getNews(@Query("apikey") apikey:String = "pub_1404870a966027d8974e05e0767f8e1fcec98"): Response<NewsResponse>

    @GET("v1/latest-news?")
    suspend fun getNews(@Header("Authorization") authorization:String = API_KEY,
                        @Query("languages") languages: String = LANGUAGE_CODE): Response<NewsResponse>
}