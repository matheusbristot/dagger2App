package com.bristot.data.client

object ApiClient : RequestHandler() {

    fun getUsersByGender(gender: String) = makeRequest(apiServices.getUsersByGender(gender))
}