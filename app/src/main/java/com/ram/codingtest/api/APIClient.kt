package com.ram.codingtest.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    val apiService: APIInterface

    init {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
//            .addInterceptor { chain ->
//                val request: Request = chain.request().newBuilder().addHeader("Authorization", "qKN7_sJYDJ2hs94yjDOiTVLWIeHjTcBc0cNVxe_6EU6Ox06c").build()
//                chain.proceed(request)
//            }
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(RequestConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(APIInterface::class.java)
    }
}
