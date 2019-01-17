package com.bristot.data.client.error

import okhttp3.ResponseBody
import java.io.IOException
import java.net.SocketTimeoutException

class RequestException private constructor(
        val errorCode: Int? = null,
        val errorMessage: String? = null,
        private val errorType: ErrorType,
        val throwable: Throwable? = null
) : Exception() {

    companion object {
        fun httpError(errorCode: Int, errorBody: ResponseBody? = null): RequestException {
            val message = ApiErrorsFormatter.deserialize(errorBody)?.error
            return RequestException(
                    errorCode = errorCode,
                    errorMessage = message,
                    errorType = ErrorType.HTTP
            )
        }

        fun networkError(exception: IOException): RequestException {
            return RequestException(errorType = ErrorType.NETWORK, throwable = exception)
        }

        fun timeoutError(exception: SocketTimeoutException): RequestException {
            return RequestException(errorType = ErrorType.TIMEOUT, throwable = exception)
        }

        fun unexpectedError(throwable: Throwable): RequestException {
            throwable.printStackTrace()
            return RequestException(
                    errorMessage = throwable.message,
                    errorType = ErrorType.UNEXPECTED,
                    throwable = throwable
            )
        }
    }

    fun isHttpError(): Boolean {
        return errorType == ErrorType.HTTP
    }

    fun isNetworkError(): Boolean {
        return errorType == ErrorType.NETWORK
    }

    fun isUnexpectedError(): Boolean {
        return errorType == ErrorType.UNEXPECTED
    }

    fun isUnauthorizedError(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.UNAUTHORIZED
    }

    fun isTimeOutException(): Boolean {
        return errorType == ErrorType.TIMEOUT || HttpError.getErrorForCode(errorCode) == HttpError.TIMEOUT
    }


    fun isNotFoundException(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.NOT_FOUND
    }

    fun isUnProcessableEntity(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.UN_PROCESSABLE_ENTITY
    }

    fun isConflictException(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.CONFLICT
    }
}

