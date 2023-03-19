package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.dto.ResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET("?name=dj")
    suspend fun getData(): ResponseDto
}