package com.bristot.data.client

import com.bristot.data.entity.ApiUser
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/api/")
    fun getUsersByGender(@Query("gender") gender: String): Single<Response<List<ApiUser>>>
}