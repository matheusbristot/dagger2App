package com.bristot.data.entity

import com.bristot.data.mapper.Mapper
import com.bristot.domain.entity.User
import com.google.gson.annotations.SerializedName

class ApiUser(
        @SerializedName("gender") val gender: String?,
        @SerializedName("name") val name: ApiName?
) {
    object ApiUserToUserMapper : Mapper<ApiUser, User>() {
        override fun transform(i: ApiUser) = User(
                gender = i.gender,
                name = i.name?.let(ApiName.ApiNameToNameMapper::transform)
        )
    }
}