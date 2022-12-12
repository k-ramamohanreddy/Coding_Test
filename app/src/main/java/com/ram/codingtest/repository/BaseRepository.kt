package com.ram.codingtest.repository

import com.ram.codingtest.api.RequestErrorCodes.NOT_FOUND
import com.ram.codingtest.api.RequestErrorCodes.SOMETHING_WRONG
import com.ram.codingtest.api.RequestErrorCodes.UNAUTHORIZED
import com.ram.codingtest.utilis.Result

abstract class BaseRepository {

    companion object {

        fun <T : Any> handleSuccess(data: T): Result<T> {
            return Result.Success(data)
        }

        fun <T : Any> handleException(code: Int): Result<T> {
            val exception = getErrorMessage(code)
            return Result.Error(Exception(exception))
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                else -> SOMETHING_WRONG
            }
        }
    }
}