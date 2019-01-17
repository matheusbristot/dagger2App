package com.bristot.data.client

import com.bristot.data.BuildConfig
import com.bristot.data.client.error.RequestException
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class RequestHandler {

    private lateinit var retrofit: Retrofit
    private var apiServiceSingleton: ApiServices? = null
    val apiServices: ApiServices get() = apiServiceSingleton ?: buildApiServices()

    protected fun <T> makeRequest(request: Single<Response<T>>): Single<T> {
        return request.compose(verifyResponseException())
                .compose(verifyRequestException())
                .compose(unwrap())
    }

    protected fun <T> justVerifyErrors(request: Single<Response<T>>): Completable {
        return request.compose(verifyResponseException())
                .compose(verifyRequestException())
                .ignoreElement()
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map(Response<T>::body)
        }
    }

    private fun <T> verifyRequestException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.onErrorResumeNext { t ->
                Single.error(when (t) {
                    is RequestException -> t
                    is SocketTimeoutException -> RequestException.timeoutError(t)
                    is UnknownHostException -> RequestException.unexpectedError(t)
                    is IOException -> RequestException.networkError(t)
                    else -> RequestException.unexpectedError(t)
                })
            }
        }
    }

    private fun <T> verifyResponseException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.doOnSuccess { response ->
                if (!response.isSuccessful) {
                    throw RequestException.httpError(response.code(), response.errorBody())
                }
            }
        }
    }


    private fun buildApiServices(): ApiServices {
        retrofit = Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(getConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        with(retrofit.create(ApiServices::class.java)) {
            apiServiceSingleton = this
            return this
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(resolveLevelInterceptor()))
        }.build()
    }

    private fun resolveLevelInterceptor(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun getConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
    }
}
