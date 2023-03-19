package com.dj.retrofitcalladapter.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
    @Json(name = "age")
    val age: Long,
    @Json(name = "count")
    val count: Long,
    @Json(name = "name")
    val name: String
)