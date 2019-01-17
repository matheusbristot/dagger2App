package com.bristot.data.client.error

import com.bristot.data.entity.ApiError
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody


object ApiErrorsFormatter {
    fun deserialize(responseBody: ResponseBody?): ApiError? {
        return try {
            Gson().fromJson(responseBody?.string(), ApiError::class.java)
        } catch (e: JsonSyntaxException) {
            null
        }
    }
}
