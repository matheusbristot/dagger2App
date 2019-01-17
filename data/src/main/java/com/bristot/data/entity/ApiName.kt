package com.bristot.data.entity

import com.bristot.data.mapper.Mapper
import com.bristot.domain.entity.Name
import com.google.gson.annotations.SerializedName

class ApiName(
        @SerializedName("title") val title: String?,
        @SerializedName("first") val first: String?,
        @SerializedName("last") val last: String?
) {
    object ApiNameToNameMapper : Mapper<ApiName, Name>() {
        override fun transform(i: ApiName) = Name(
                title = i.title,
                first = i.first,
                last = i.last
        )
    }
}